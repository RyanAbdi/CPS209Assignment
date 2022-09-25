import java.util.*;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    private ArrayList<Product>  products = new ArrayList<Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = null;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
    	
    	// Create some products. Notice how generateProductId() method is used
    	products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
    	products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
    	products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney"));
    	products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
    	products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
    	products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
    	products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast"));
    	products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive"));
    	products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney"));
    	products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
        products.add(new Shoes("Shoes", generateProductId(), 10.0, 22, "10", "Black"));
    	
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }
    
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    public String getErrorMessage()
    {
    	return errMsg;
    }
    
    public void printAllProducts()
    {
    	for (Product p : products)
    		p.print();
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
        for (Product p : products){
            if (p.getCategory() == Product.Category.BOOKS){
                p.print();
            }
        }
    }
    // Print all current orders
    public void printAllOrders()
    {
    	for(ProductOrder ord : orders)
        {
            ord.print();
        }
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {

        for (ProductOrder shipped: shippedOrders)
        {
            shipped.print();
        }
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c: customers)
            c.print();
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public boolean printOrderHistory(String customerId)
    {
        boolean passed = false;
      // Make sure customer exists - check using customerId
    	// If customer does not exist, set errMsg String and return false
    	// see video for an appropriate error message string
    	// ... code here
        for (Customer c : customers)
        {
            if (c.getId().equals(customerId)) {
                passed = true;
                break;
            }
        }
        if(!passed)
        {
            errMsg = "Customer ID not found";
            return false;
        }


    	// Print current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
        for (ProductOrder order : orders) {
            if (order.getCustomer().getId().equals(customerId)) {
                order.print();
            }
        }
    	
    	// Print shipped orders of this customer 
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
        for (ProductOrder shippedOrder : shippedOrders) {
            if (shippedOrder.getCustomer().getId().equals(customerId)) {
                shippedOrder.print();
            }
        }
    	return true;
    }
    
    public String orderProduct(String productId, String customerId, String productOptions)
    {
        boolean passed = false;
        Customer cust = null;
        errMsg = null;
    	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object
    	for(Customer c : customers)
        {
            if(c.getId().equals(customerId))
            {
                passed = true;
                cust = c;
            }
        }
        if(!passed)
        {
            errMsg = "Customer ID not found";
            return null;
        }

    	// Check to see if product object with productId exists in array list of products
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Product object
        passed = false;
        Product prod = null;
        for(Product p : products)
        {
            if(p.getId().equals(productId))
            {
                passed = true;
                prod = p;
            }
        }
        if(!passed)
        {
            errMsg = "Product ID not found";
            return null;
        }

        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
    	// If options are not valid, set errMsg string and return null;
    	if(!prod.validOptions(productOptions))
        {
            errMsg = "Options not valid";
            return null;
        }


    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
    	if(prod.getStockCount(productOptions) <= 0 )
        {
            errMsg = "Not enough Stock";
            return null;
        }
      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
        ProductOrder productOrder = new ProductOrder(generateOrderNumber(), prod, cust, productOptions);
        prod.reduceStockCount(productOptions);
        orders.add(productOrder);
    	return productOrder.getOrderNumber();

    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public boolean createCustomer(String name, String address)
    {
        if (name == null || name.equals("")){
            errMsg = "Invalid Customer Name";
            return false;
        }
        if (address == null || address.equals("")){
            errMsg = "Invalid Customer Address";
            return false;
        }
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	// Repeat this check for address parameter
    	Customer cust = new Customer(generateCustomerId(),name,address);
    	// Create a Customer object and add to array list
        customers.add(cust);
    	return true;
    }
    
    public ProductOrder shipOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
        boolean passed = false;
        ProductOrder ord = null;
        int index = 0;
        for(int i = 0; i < orders.size(); i++)
        {
            ProductOrder order = orders.get(i);
            if(order.getOrderNumber().equals(orderNumber))
            {
                passed = true;
                ord = order;
                index = i;
            }
        }
        if(!passed)
        {
            errMsg = "Order " + orderNumber + " Not Found";
            return null;
        }

    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order
        orders.remove(index);
        shippedOrders.add(ord);
    	return null;
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false

        boolean passed = false;
        ProductOrder ord = null;
        int index = 0;
        for(int i = 0; i < orders.size(); i++)
        {
            ProductOrder order = orders.get(i);
            if(order.getOrderNumber().equals(orderNumber))
            {
                passed = true;
                ord = order;
                index = i;

            }
        }
        if(!passed)
        {
            errMsg = "Order " + orderNumber + " Not Found";
            //System.out.println(errMsg);
            return false;
        }

        orders.remove(index);
    	return true;
    }
    
    // Sort products by increasing price
    public void sortByPrice()
    {
        products.sort((o1, o2) -> {
            Double o1price = (o1.getPrice() * 10);
            Double o2price = (o2.getPrice() * 10);
            return o1price.compareTo(o2price);
        });
        printAllProducts();
    }
    
    
    // Sort products alphabetically by product name
    public void sortByName()
    {
  	  products.sort(Comparator.comparing(Product::getName));
      printAllProducts();
    }
    
        
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
  	  customers.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
      printCustomers();
    }
}
