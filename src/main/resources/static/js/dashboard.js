document.addEventListener('DOMContentLoaded', () => {
    const tableBody = document.querySelector('#eventTable tbody');
    const createEventBtn = document.getElementById('createEventBtn');
    const modal = document.getElementById('eventModal');
    const closeModalBtn = document.querySelector('.close-btn');
    const eventForm = document.getElementById('eventForm');
    const assignTaskBtn = document.getElementById('assignTaskBtn');
    const taskModal = document.getElementById('taskModal');
    const closeTaskModalBtn = document.querySelector('.close-task-btn');
    const taskForm = document.getElementById('taskForm');

    /*
    JavaScript async/await and try/catch - Beginner's Guide, (Jan 2023), published by @uniswann on YouTube
     */

    // Function to fetch events from the backend
    async function fetchEvents() {
        try {
            // Fetch data from the backend
            const response = await fetch('/api/events');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const events = await response.json();

            /*
            "JavaScript/HTML Dynamic Tables", published by Dennis Ivy, available from YouTube (Nov 2019)
             */
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

    /*
    // Call the function to fetch and display events
    fetchEvents();
     */

    //Corrected calling - Ensure fetchEvents is called and awaited properly
    (async () => {
        await fetchEvents(); // Await the fetchEvents call to ensure events are fetched first
    })();


    // Show modal
    createEventBtn.addEventListener('click', () => {
        modal.style.display = 'flex';
    });

    // Hide modal
    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    /*
    ChatGPT - Query: I am trying to create a button called "Create a new Event" but I am trying to find a way to submit the info and allow it to Post the information to the database, then update the table
     */
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

            // Await fetchEvents to ensure it fetches updated data after the new event is created
            await fetchEvents(); // This ensures the table is updated after adding the new event

            // Refresh the table and close the modal
            modal.style.display = 'none';
            eventForm.reset();
        } catch (error) {
            console.error('Error creating event:', error);
        }
    });


    // Show modal
    assignTaskBtn.addEventListener('click', () => {
        taskModal.style.display = 'flex';
    });

    // Hide modal
    closeTaskModalBtn.addEventListener('click', () => {
        taskModal.style.display = 'none';
    });


    // Submit new task
    taskForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const newTask = {
            eventID: taskForm.eventID.value,
            taskName: taskForm.taskName.value,
            taskDescription: taskForm.taskDescription.value,
            dueDate: taskForm.dueDate.value,
        };

        try {
            const response = await fetch(`/events/${newTask.eventID}/assign-task`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams(newTask).toString(),
            });

            if (!response.ok) {
                throw new Error('Failed to assign task');
            }

            taskModal.style.display = 'none';
            taskForm.reset();
            alert('Task assigned successfully!');
        } catch (error) {
            console.error('Error assigning task:', error);
        }
    });
});





    /*
    dashboard.js is created using ChatGPT - Query: "Create a JavaScript page to integrate my EventDashboard and enable functionality for the buttons and modals"
     */



