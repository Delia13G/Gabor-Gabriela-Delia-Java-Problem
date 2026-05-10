Java Train Ticketing System

This is a comprehensive Java console apploication designed to manage a railway ticketing system.
The application allows customers to search for direct and transfer routes and book tickets while preventing overbooking.
It also provides an Administrator panal to manage the railway network (trains, routes,stations) and handle live operation such as marking trains as delayed and
notifying passengers.

Input/Output Examples

1.Customers: Find Direct Connections
Input: Departure Station: Cluj
       Arrival Station: Sibiu
Output: "Searching direct routes"
`[TrainSchedule{id='SCH-1', train=TRN-1, route=R-NORTH, departureDate=2024-05-10T14:30, isDelayed=false, totalBookings=0}]`

2. Customer: Find Connections with Transfers
Input:
   Departure Station: `Cluj`
   Arrival Station: `Timisoara`
Output:
   `Searching transfer routes`
   `Found Transfer Route!`
   `1. Take TRN-1 from Cluj to Alba Iulia`
   `2. Change at Alba Iulia`
   `3. Take TRN-2 to Timisoara`
3. Customer: Book a Ticket 
Input:
   Schedule ID: `SCH-1`
   Email: `test@mail.com`
   Start Station: `Cluj`
   End Station: `Sibiu`
   Number of Tickets: `2`
Output:
   `MOCK EMAIL`
   `To: test@mail.com`
   `Subject: Booking Confirmation`
   `Message: You have successfully booked 2 tickets from Cluj to Sibiu.`
4. Administrator: Add a New Train
Input:
   Train ID: `TRN-99`
   Total Capacity: `200`
Output:
   `Success: Train TRN-99 has been added.`
5. Administrator: Add Station to a Route
Input:
   Route ID: `R-NORTH`
   Station Name: `Brasov`
   Arrival Time: `16:00`
   Departure Time: `16:30`
Output:
   `Success: Added stop Brasov to route R-NORTH.`
6. Administrator: Modify Route (Remove a Stop)
Input:
   Route ID to modify: `R-NORTH`
   Choice (a to add, d to delete): `d`
   Station Name to remove: `Sibiu`
Output:
   `Success: Station 'Sibiu' was removed from route 'R-NORTH'.`
7. Administrator: View Bookings
Input:
   Schedule ID: `SCH-1`
Output:
   `[Booking{customerEmail='test@mail.com', numberOfTickets=2, startStation='Cluj', endStation='Sibiu'}]`
8. Administrator: Mark Train as Delayed 
Input:
   Schedule ID: `SCH-1`
Output: (System automatically loops through bookings and emails customers)
   `MOCK EMAIL`
   `To: test@mail.com`
   `Subject: URGENT: Train Delayed`
   `Message: We are sorry to inform you that your train from Cluj to Sibiu has been delayed.`
