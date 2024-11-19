document.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.querySelector('#eventTable tbody');
    const createEventBtn = document.getElementById('createEventBtn');
    const modal = document.getElementById('eventModal');
    const closeModalBtn = document.querySelector('.close-btn');
    const eventForm = document.getElementById('eventForm');

    // Function to fetch events from the backend
    async function fetchEvents() {
        try {
            // Fetch data from the backend
            const response = await fetch('/api/events');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const events = await response.json();

            // Populate the table with data
            tableBody.innerHTML = ''; // Clear any existing rows
            events.forEach(event => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${event.eventID}</td>
                    <td>${event.eventName}</td>
                    <td>${event.eventDate}</td>
                    <td>${event.eventStatus}</td>
                `;
                tableBody.appendChild(row);
            });
        } catch (error) {
            console.error('Error fetching events:', error);
        }
    }

    // Show modal
    createEventBtn.addEventListener('click', () => {
        modal.style.display = 'flex';
    });

    // Hide modal
    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    // Submit new event
    eventForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const newEvent = {
            eventID: eventForm.eventID.value,
            eventName: eventForm.eventName.value,
            eventDate: eventForm.eventDate.value,
            eventStatus: eventForm.eventStatus.value,
        };

        try {
            const response = await fetch('/api/events', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newEvent),
            });

            if (!response.ok) {
                throw new Error('Failed to create new event');
            }

            // Refresh the table and close the modal
            fetchEvents();
            modal.style.display = 'none';
            eventForm.reset();
        } catch (error) {
            console.error('Error creating event:', error);
        }
    });

    // Call the function to fetch and display events
    fetchEvents();
});

