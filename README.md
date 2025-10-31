# Product Microservice

A Spring Boot microservice for managing products with dynamic search capabilities.

## Features

- ✅ CRUD operations for products
- 🔍 Dynamic search with multiple filter options
- 📊 Advanced queries using JPQL
- 🚀 Built with Spring Boot 3.x
- 🗃️ Uses H2 in-memory database (for development)
- 🛠️ Maven-based project

## API Endpoints

### Product Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/products` | Get all products |
| GET    | `/products/{SKU}` | Get product by SKU |
| GET    | `/products/description/{description}` | Get product by description |
| POST   | `/products` | Create a new product |
| PUT    | `/products/{sku}` | Update an existing product |
| PATCH  | `/products/{sku}` | Partially update a product |
| DELETE | `/products/{sku}` | Delete a product |

### Search Endpoints

| Method | Endpoint | Query Parameters | Description |
|--------|----------|------------------|-------------|
| GET    | `/products/search` | `keyword` | Search products by description (case-insensitive) |
| GET    | `/products/search-by-price` | `minPrice` | Find products with price greater than minPrice |
| GET    | `/products/search-by-price-range` | `minPrice`, `maxPrice` | Find products within price range |
| GET    | `/products/search-by-sku-and-price` | `sku`, `maxPrice` | Find by SKU and price less than maxPrice |
| GET    | `/products/top-5` | - | Get 5 cheapest products |
| GET    | `/products/search-jpql` | `keyword` | Search using JPQL |
| GET    | `/products/count-by-price` | `price` | Count products with price greater than value |
| GET    | `/products/dynamic-search` | `description`, `minPrice`, `maxPrice` | Dynamic search with optional filters |

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- (Optional) Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/product-microservice.git
   cd product-microservice
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080` by default.

## Database

The application uses an in-memory H2 database by default. The H2 console is available at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:productdb`
- Username: `sa`
- Password: (leave empty)

## Example Requests

### Create a new product
```bash
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{
    "sku": 1001,
    "description": "Smartphone XYZ",
    "price": 599.99,
    "stock": 50
  }'
```

### Dynamic search example
```bash
# Search for products containing 'phone' with price between 100 and 1000
curl "http://localhost:8080/products/dynamic-search?description=phone&minPrice=100&maxPrice=1000"
```

## Project Structure

```
src/main/java/tech/ada/product_microservice/
├── config/           # Configuration classes
├── controller/       # REST controllers
├── model/            # Entity classes
├── repository/       # Data access layer
├── service/          # Business logic
└── ProductMicroserviceApplication.java  # Main application class
```

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with ❤️ using Spring Boot
- Thanks to all contributors who have helped with this project

---

<div align="center">
  <p>Made with ❤️ by Your Name</p>
  <p>📧 your.email@example.com</p>
</div>
