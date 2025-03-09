# Online Bus Ticket Booking

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [Contact](#contact)

## Introduction

Welcome to the Online Bus Ticket Booking system! This application allows users to book bus tickets online, offering a seamless and convenient way to travel.

## Features

- User registration and login
- Search for available buses
- Select seats and book tickets
- View booking history
- Payment gateway integration
- Admin dashboard for managing buses and bookings

## Installation

### Prerequisites

- Node.js
- npm
- MongoDB

### Steps

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/online-bus-ticket-booking.git
   ```
2. Navigate to the project directory:
   ```sh
   cd online-bus-ticket-booking
   ```
3. Install the dependencies:
   ```sh
   npm install
   ```
4. Set up environment variables:
   Create a `.env` file in the root directory and add the following:
   ```env
   PORT=3000
   MONGODB_URI=your_mongodb_uri
   JWT_SECRET=your_jwt_secret
   ```
5. Start the application:
   ```sh
   npm start
   ```

## Usage

1. Open your browser and go to `http://localhost:3000`.
2. Register a new account or log in with an existing account.
3. Search for buses by entering your departure and destination cities along with the travel date.
4. Select a bus and choose your seats.
5. Proceed to payment to confirm your booking.
6. View your booking history in your profile.

## Technologies Used

- Node.js
- Express.js
- MongoDB
- Mongoose
- JWT (JSON Web Tokens)
- React.js (for the front end)
- Redux (for state management)
- Stripe (for payment processing)

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch:
   ```sh
   git checkout -b feature-name
   ```
3. Make your changes and commit them:
   ```sh
   git commit -m "Add feature"
   ```
4. Push to the branch:
   ```sh
   git push origin feature-name
   ```
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE] file for details.

## Contact

For any questions or suggestions, please contact:

- Your Name: harshithravi1999@gmail.com
- GitHub:KingMakerHarshh (https://github.com/KingMakerHarshh/Online-Bus-Ticket/).
