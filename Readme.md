# Human Resources Management System

This repository contains the source code for a comprehensive Human Resources Management System (HRMS). The project focuses on developing a robust microservice architecture to effectively manage various HR processes and enhance the overall efficiency of HR management.

## Backend

The backend components of the HRMS system are built using Spring Frameworks and utilize various technologies for data storage, messaging, and deployment.

### Technologies Used

- Spring Frameworks (Spring Boot, Spring Data, etc.)
- Gradle (Dependency management and build tool)
- PostgreSQL (Relational database for data storage)
- MongoDB (NoSQL database for data storage)
- RabbitMQ (Message broker for asynchronous communication)
- Zipkin (Distributed tracing and performance monitoring)

### Login & Forgot Password Pages
<div>
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/2ef60e86-f6a2-4a2a-824b-2a282ef21206" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/78a580bc-05f2-4ccc-9e2d-119f11a3d781" alt="Alt Text" width="500">
</div>

### Admin 
<div>
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/4ac1797b-8f06-41c5-a3b2-fdb04177e706" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/b55da171-ae25-49f1-b9cf-f047b237e749" alt="Alt Text" width="500">
</div>

### Manager 
<div>
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/9e5d45b7-f1e1-4039-a963-e9fedc53d725" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/74f261f9-d088-4f84-aeb3-24041eb14dec" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/9ca19aa9-ceb1-4159-90e3-acd68e0a0b78" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/e07a0ee8-2855-4f20-a86b-92f4035b3d5e" alt="Alt Text" width="500">
</div>

### Employee
<div>
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/bf2f9e3e-9521-400c-a238-0224f08b7503" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/e4014a70-a22f-4856-845d-aeb55845b173" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/2a52de47-53c5-40cf-96ef-5a0c1bc1f6f5" alt="Alt Text" width="500">
<img src="https://github.com/HumanResourcesProject/FrontEnd/assets/116001471/d1fc29a0-b9d9-4547-ba28-3e14899c0b43" alt="Alt Text" width="500">
</div>

### Prerequisites

Before running the HRMS backend services, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK) version 17 or above.
- Gradle - Ensure that Java and Gradle are installed correctly and the JAVA_HOME environment variable is properly set.
- PostgreSQL database
- MongoDB database
- RabbitMQ message broker

### Getting Started
To get started with the HRMS backend services, follow these steps:

1. Clone this repository to your local machine using `git clone https://github.com/HumanResourcesProject/HumanResourcesProject.git`.

2. Navigate to the project directory: `cd HumanResourcesProject/HumanResourcesProject`.

3. Configure the backend components:
   - Open the `src/main/resources/application.yml` file and update the necessary configuration settings for database connections, RabbitMQ, etc.

4. Run the backend services:
   - ConfigServer
   - AdminMicroService
   - AuthMicroService
   - CompanyMicroService
   - EmployeeMicroService
   - MailService
   - ManagerMicroService
   - RequirementMicroService

Note: Make sure to start each microservice in the correct order as per the dependencies they have.

## Frontend

The frontend of the HRMS system is built using React and leverages Axios for making HTTP requests to the backend APIs.

### Technologies Used

- React (JavaScript library for building user interfaces)
- Axios (HTTP client for making API requests)

### Prerequisites

Before running the HRMS frontend, ensure that you have the following prerequisites installed:

- Node.js (with npm)

### Getting Started

To get started with the HRMS frontend, follow these steps:

1. Clone this repository to your local machine using `git clone https://github.com/HumanResourcesProject/FrontEnd.git`.

2. Navigate to the project directory: `cd HumanResourcesProject/FrontEnd`.

3. Configure the frontend components:
   - Open the `src/config.js` file and set the appropriate backend API endpoints.

4. Install the dependencies:
   - Run `npm install` to install the necessary packages.

5. Start the frontend development server:
   - Run `npm start` to start the development server.

6. Access the HRMS system by visiting `http://localhost:3000` in your web browser.

## Contribution Guidelines

If you would like to contribute to the HRMS project, please follow these guidelines:

1. Fork the repository and create your branch: `git checkout -b feature/my-feature`.

2. Commit your changes: `git commit -am 'Add some feature'`.

3. Push to the branch: `git push origin feature/my-feature`.

4. Submit a pull request detailing your changes and their purpose.

## Contact

For any inquiries or questions, feel free to reach out to the project team:

- Buse Cankaya - [LinkedIn](https://linkedin.com/in/buse-cankaya/) - [GitHub](https://github.com/busecnky)
- Email: cankayabuse94@gmail.com

- Irfan Mert Namsal - [LinkedIn](https://linkedin.com/in/irfanmertnamsal/) - [GitHub](https://github.com/mertnamsal)
- Email: namsalmert@gmail.com

- Muhammed Furkan Turkmen - [LinkedIn](https://linkedin.com/in/muhammedfurkanturkmen/) - [GitHub](https://github.com/MFurkanTurkmen)
- Email: f.turkmen06@gmail.com

- Oguz Tasgin - [LinkedIn](https://linkedin.com/in/oguztasgin/) - [GitHub](https://github.com/oguztasgin1)
- Email: tasginoguz@gmail.com

We appreciate your interest and contributions to the HRMS project!

## License

The HRMS project is licensed under the [MIT License](LICENSE). Feel free to modify and distribute the code for your own purposes.





