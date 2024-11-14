document.addEventListener("DOMContentLoaded", () => {
    const eventListContainer = document.getElementById("eventList");
    const createEventBtn = document.getElementById("createEventBtn");

    // Redirect to create event page
    createEventBtn.addEventListener("click", () => {
        window.location.href = "createEvent.html";
    });

    // Fetch events from the server
    async function fetchEvents() {
        try {
            const response = await fetch("/api/events"); // Adjust this endpoint if needed
            if (!response.ok) {
                throw new Error("Failed to fetch events");
            }
            const events = await response.json();
            renderEvents(events);
        } catch (error) {
            console.error(error);
            eventListContainer.innerHTML = "<p>Error loading events. Please try again later.</p>";
        }
    }

    // Render events onto the dashboard
    function renderEvents(events) {
        eventListContainer.innerHTML = ""; // Clear existing content
        events.forEach(event => {
            const eventCard = document.createElement("div");
            eventCard.className = "event-card";
            eventCard.innerHTML = `
                <h2>${event.eventName}</h2>
                <p>Date: ${event.eventDate}</p>
                <p>Status: ${event.eventStatus}</p>
                <div class="progress-bar">
                    <div class="progress-bar-inner" style="width: ${event.eventCompletionPercentage}%;"></div>
                </div>
                <p>${event.eventCompletionPercentage}% Complete</p>
            `;
            eventListContainer.appendChild(eventCard);
        });
    }

    // Call the function to fetch and display events
    fetchEvents();
});
