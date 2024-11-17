package com.fyp.SpringSophie2.Servlet;

import com.fyp.SpringSophie2.model.Event;
import com.fyp.SpringSophie2.Service.EventService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/events")
public class EventServlet extends HttpServlet {

    private EventService eventService;

    @Override
    public void init() throws ServletException {
        // Initialize EventService when the servlet starts
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all events from the database
        try {
            List<Event> events = EventService.getAllEvents();
            request.setAttribute("events", events);
            request.getRequestDispatcher("/WEB-INF/views/eventTable.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Failed to load events", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        // Add a new event to the database
        try {
            if ("create".equals(action)) {

                String eventID = request.getParameter("eventID");
                String eventName = request.getParameter("eventName");
                LocalDate eventDate = LocalDate.parse(request.getParameter("eventDate"));
                String eventStatus = request.getParameter("eventStatus");

                Event event = new Event(eventID, eventName, eventDate, eventStatus);
                eventService.addEvent(event);

                response.sendRedirect("/events");

            } else if ("delete".equals(action)) {
                // Delete an event
                String eventID = request.getParameter("eventID");
                eventService.deleteEvent(eventID);

                // Redirect to events page after deletion
                response.sendRedirect("/events");
            } else {
                throw new ServletException("Invalid action: " + action);
            }
        } catch (Exception e) {
            throw new ServletException("Failed to process event action", e);
        }


    }


}

