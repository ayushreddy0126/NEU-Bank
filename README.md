# NEU-Bank

![NEU-Bank Logo](https://via.placeholder.com/150?text=NEU+Bank)

## Overview

NEU-Bank is a comprehensive banking application designed to provide secure and efficient banking services. This platform offers a modern banking experience with features tailored for account management, transactions, and financial analysis.

## Features

### User Features
- 🔐 **Secure Authentication**: Multi-factor authentication for enhanced security
- 💳 **Account Management**: Create and manage checking, savings, and investment accounts
- 💸 **Money Transfers**: Seamless transfers between accounts and to external recipients
- 📊 **Financial Dashboard**: Real-time visualization of financial status and transaction history
- 📱 **Mobile Responsiveness**: Access banking services on any device
- 🔔 **Notifications**: Real-time alerts for account activities and important updates

### Admin Features
- 👥 **User Management**: Create, update, and manage user profiles and permissions
- 📈 **Transaction Monitoring**: Track and analyze transaction patterns
- 🛠️ **System Configuration**: Adjust system parameters and settings
- 📝 **Audit Logs**: Comprehensive tracking of all system activities
- 📊 **Reporting Tools**: Generate financial and operational reports

## Tech Stack

### Frontend
- React.js
- Redux for state management
- Material-UI components
- Chart.js for financial data visualization

### Backend
- Node.js with Express
- RESTful API architecture
- JWT for authentication
- MongoDB for data storage

### Security
- HTTPS encryption
- Password hashing
- Rate limiting
- Input validation and sanitization
- CSRF protection

## Getting Started

### Prerequisites
- Node.js (v14 or higher)
- npm or yarn
- MongoDB (v4.4 or higher)

### Local Development Setup

1. Clone the repository
   ```bash
   git clone https://github.com/ayushreddy0126/NEU-Bank.git
   cd NEU-Bank
   ```

2. Install dependencies
   ```bash
   # Install backend dependencies
   cd server
   npm install

   # Install frontend dependencies
   cd ../client
   npm install
   ```

3. Set up environment variables
   ```bash
   # In the server directory, create a .env file
   cp .env.example .env
   # Edit the .env file with your database and JWT credentials
   ```

4. Start MongoDB
   ```bash
   # Make sure MongoDB is running on your system
   mongod --dbpath /path/to/your/data/directory
   ```

5. Start development servers
   ```bash
   # Start backend server (from server directory)
   npm run dev

   # Start frontend server (from client directory)
   npm start
   ```

6. Access the application at `http://localhost:3000`

## API Endpoints

The application provides the following RESTful API endpoints:

### Authentication
- `POST /api/auth/register`: Register a new user
- `POST /api/auth/login`: Authenticate a user
- `POST /api/auth/logout`: Log out a user
- `POST /api/auth/refresh-token`: Refresh authentication token

### User Management
- `GET /api/users/:id`: Get user information
- `PUT /api/users/:id`: Update user information
- `DELETE /api/users/:id`: Delete a user account

### Account Management
- `GET /api/accounts`: Get all accounts for a user
- `GET /api/accounts/:id`: Get specific account details
- `POST /api/accounts`: Create a new account
- `PUT /api/accounts/:id`: Update account information
- `DELETE /api/accounts/:id`: Close an account

### Transactions
- `GET /api/transactions`: Get transaction history
- `GET /api/transactions/:id`: Get transaction details
- `POST /api/transactions`: Create a new transaction
- `GET /api/transactions/summary`: Get transaction summary

Detailed API documentation is available in the [API Documentation](docs/api.md) file.

## Database Schema

### Users Collection
- `_id`: Unique identifier
- `firstName`: User's first name
- `lastName`: User's last name
- `email`: User's email address (unique)
- `password`: Hashed password
- `phoneNumber`: User's phone number
- `address`: User's address information
- `createdAt`: Account creation timestamp
- `updatedAt`: Last update timestamp

### Accounts Collection
- `_id`: Unique identifier
- `userId`: Reference to the user owner
- `accountType`: Type of account (checking, savings, etc.)
- `accountNumber`: Unique account number
- `balance`: Current account balance
- `currency`: Account currency
- `isActive`: Account status
- `createdAt`: Account creation timestamp
- `updatedAt`: Last update timestamp

### Transactions Collection
- `_id`: Unique identifier
- `fromAccount`: Source account reference
- `toAccount`: Destination account reference
- `amount`: Transaction amount
- `currency`: Transaction currency
- `type`: Transaction type (deposit, withdrawal, transfer)
- `status`: Transaction status
- `description`: Transaction description
- `createdAt`: Transaction timestamp

## Testing

```bash
# Run backend tests
cd server
npm test

# Run frontend tests
cd client
npm test

# Run end-to-end tests
npm run test:e2e
```

## Deployment

### Production Deployment

1. Build the frontend
   ```bash
   cd client
   npm run build
   ```

2. Set up production environment variables
   ```bash
   # Configure production environment variables
   export NODE_ENV=production
   export MONGODB_URI=your_production_mongodb_uri
   export JWT_SECRET=your_production_jwt_secret
   ```

3. Start the production server
   ```bash
   cd server
   npm start
   ```

Detailed deployment instructions are available in the [Deployment Guide](docs/deployment.md).

## Project Structure

```
neu-bank/
├── client/                 # Frontend React application
│   ├── public/             # Static files
│   └── src/                # React source code
│       ├── components/     # Reusable UI components
│       ├── pages/          # Application pages
│       ├── redux/          # Redux state management
│       ├── services/       # API service integrations
│       └── utils/          # Utility functions
├── server/                 # Backend Node.js/Express application
│   ├── config/             # Configuration files
│   ├── controllers/        # Request handlers
│   ├── middleware/         # Express middleware
│   ├── models/             # MongoDB models
│   ├── routes/             # API routes
│   └── services/           # Business logic services
├── docs/                   # Documentation
└── scripts/                # Utility scripts
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and contribution process.

## Team

- **Mandadi Ayush Reddy**
- [Other Team Members]

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [MongoDB](https://www.mongodb.com/) for database solutions
- [Express.js](https://expressjs.com/) for API framework
- [React.js](https://reactjs.org/) for frontend framework
- All contributors who have helped shape this project
