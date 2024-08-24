# Hotel-Management-System
This is an assignment given in the Patika  Intermediate Java Course

Patika Tourism Agency requires a digital infrastructure to manage its operations. Assume you have agreed to develop this digital infrastructure for Patika.

The primary goal of this project is to help a company operating in the hotel industry manage its daily operations more effectively and optimize customer reservation processes.

Patika Tourism Agency collaborates with multiple hotels and reserves rooms for customers. The first admin user with administrative privileges will be added to the database by the developer. The admin can log in to the system and add both admins and agency staff. Agency staff will be responsible for registering hotels and rooms into the system, searching for rooms according to customer requests, and managing reservations. Consider a scenario where customers do not access the system directly but receive support via phone or face-to-face interaction.

The Hotel Management System aims to provide agency staff with a user-friendly interface offering features like flexible pricing, detailed hotel and room management, simplified reservations, and automated calculations.

The agency believes that handling these tasks manually on paper is time-consuming and needs software to streamline its operations.

## Project Requirements
The project will define two types of users: Admin and Agency Staff, each with the following roles:

### Admin

User Management: When logged in with admin privileges, an admin panel will be accessible. From this panel, the admin can:

List agency staff,
Add staff,
Delete staff,
Update staff details,
Filter users based on their role (admin or staff).
Agency Staff

Hotel Management: List and add hotels. 
Room Management: List and add rooms. 
Period Management: List and add periods. 
Price Management
Room Search 
Reservation Management: List, add, delete, and update reservations.

### User Management

User management allows the admin to add, remove, and update users who can access the system. Users log in to the system using a username and password.

When adding a new user, the admin specifies the user’s role (admin or staff).
The admin can update existing user information (name, surname, password, etc.).
The admin can delete user accounts.
The admin can filter users by their role (admin or staff).
Hotel Management
The agency should manage the hotels it partners with, including location details and other attributes. When adding a hotel, details like Hotel Name, Address, Email, Phone, Star Rating, Facility Features, and Pension Types should be defined.

The hotel management screen will include a list of hotels, where new hotels can be added, and existing hotels can be updated with their facility features, pension types, and period information.

Expected Pension Types in the System:

Ultra All-Inclusive
All-Inclusive
Bed and Breakfast
Full Board
Half Board
Room Only
Alcohol-Free Full Credit
Expected Facility Features in the System:

Free Parking
Free WiFi
Swimming Pool
Fitness Center
Hotel Concierge
SPA
24/7 Room Service
Sample Hotel Data:

Hotel Name: Kodluyoruz Life Istanbul
City: Istanbul
Region: Beyoğlu
Full Address: Şahkulu, Şişhane Metro Durağı, Meşrutiyet Cd. No:125, 34421
Email: info@kodluyoruz.org
Phone: 0212 xxx xx xx
Star Rating: 5 Stars
Facility Features:

Free Parking
SPA
24/7 Room Service
Pension Types:

Bed and Breakfast
Half Board

---

### Period Management

Historical periods for hotels are added, and room pricing takes these periods into account. The goal here is to offer variable pricing. For example, hotel prices are higher during the summer months and lower in the winter months. Pricing in the tourism sector is typically seasonal. Periods are defined as two date ranges.

Agency staff enter periods as date ranges. Room pricing will vary according to these periods.

**Example Periods:**
- 01/01/2021 - 31/05/2021
- 01/06/2021 - 01/12/2021

### Room Management

Agency staff add the rooms they have booked from hotels into the system and provide pricing for these rooms. The types of rooms available in hotels are limited to four categories: Single Room, Double Room, Junior Suite, and Suite. Instead of adding the same type of room multiple times, a stock-based approach is used. Additionally, room features must also be entered. The room features are detailed below. The room screen includes a list of all rooms. Staff can add rooms to hotels and search for rooms for reservations through this screen. When adding a room, the hotel, one of the four room types, one of the registered pension types, and one of the registered periods for the hotel must be selected. Based on these selections, the per-night price for adults, per-night price for children, and stock quantity of the room are entered. Additionally, features such as the number of beds, square meters, and other room attributes (like the presence of a television, minibar, game console, safe, and projector) should be recorded. All room information should be displayed on the room listing screen.

**Expected Room Features in the System:**
- Number of Beds
- Square Meters
- Television (Yes, No)
- Minibar (Yes, No)
- Game Console (Yes, No)
- Safe (Yes, No)
- Projector (Yes, No)

The capacity of a room is determined by the number of beds. A room with 2 beds can accommodate 2 guests.

### Room Pricing

Rooms are priced on a per-night basis. Room prices will vary based on the periods and pension types added for the hotel. Separate pricing will be defined for adults and children.

**Expected Pricing in the System:**
- Prices are defined based on the nightly rates for rooms.

---

### Room Search and Reservation Processing

Agency staff should be able to search for rooms in the system based on:

- The entered date range,
- The city,
- The hotel name.

Search functionality should allow for queries based on one, two, or all three of these criteria. You will need to write the necessary dynamic SQL queries to support this functionality.

---

### Room Search Algorithm

After entering the necessary room search information, agency staff can list the rooms available in the system.

For a room to appear in the list:

1. The hotel associated with the room must be located in the desired city.
2. The hotel must have a period that corresponds to the desired date range. For example, for a search with a check-in date of 09/06/2021 and a check-out date of 12/06/2021, the hotels should have a period that covers this date range.
3. If the hotel has a relevant period, the room must have pricing information according to the pension types for those periods to provide pricing details to the customer.
4. The room's stock quantity must be greater than 0.

**Example Search Data:**

- **City**: Istanbul
- **Check-In Date**: 01/04/2021
- **Check-Out Date**: 03/04/2021
- **Guest Information**: 2 Adults, 1 Child

### Price Calculation

Prices are calculated based on guest information, the number of nights stayed, and the room rate.

Based on this information:

1. Hotels in the entered city are first identified.
2. Rooms with available stock in the identified hotels are located.
3. If the rooms have period pricing information for the relevant date range, the price calculation is performed.

**Example Room Pricing:**

### Room Search Algorithm

After entering the necessary room search information, agency staff can list the rooms available in the system.

For a room to appear in the list:

1. The hotel associated with the room must be located in the desired city.
2. The hotel must have a period that corresponds to the desired date range. For example, for a search with a check-in date of 09/06/2021 and a check-out date of 12/06/2021, the hotels should have a period that covers this date range.
3. If the hotel has a relevant period, the room must have pricing information according to the pension types for those periods to provide pricing details to the customer.
4. The room's stock quantity must be greater than 0.

**Example Search Data:**

- **City**: Istanbul
- **Check-In Date**: 01/04/2021
- **Check-Out Date**: 03/04/2021
- **Guest Information**: 2 Adults, 1 Child

### Price Calculation

Prices are calculated based on guest information, the number of nights stayed, and the room rate.

Based on this information:

1. Hotels in the entered city are first identified.
2. Rooms with available stock in the identified hotels are located.
3. If the rooms have period pricing information for the relevant date range, the price calculation is performed.

---

### Reservation Process

After listing the rooms with the desired features, the agency user proceeds to the reservation process for the room selected by the customer. During the reservation process, the total price should be automatically calculated, and contact information from one of the customers should be collected to complete the reservation.

To complete the reservation:

1. Customer contact information
2. Guest’s name, surname, and ID information (e.g., T.C. Kimlik)

The sale is completed through the system. If the sale is completed, the stock of the relevant room decreases by 1.

Agency staff will be able to list, update, and delete reservations within the system. When a reservation is deleted, the stock of the related room should increase by 1.

---

### Technical Requirements

- **Database**: You may use MySQL or PostgreSQL for the project.
- **GUI (Interface)**: A graphical user interface should be designed (e.g., using Swing, JavaFX).
- **Design Independence**: Design the interface independently of example images.
- **User-Friendly Design**: The interface design should be user-friendly, and you should use your own design.

---