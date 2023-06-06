package onlineretailsystem;

import onlineretailsystem.domain.CreditCard;
import onlineretailsystem.domain.Customer;
import onlineretailsystem.repository.CustomerRepository;
import onlineretailsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create customer
		Customer customer1 = new Customer("Mosenith", "pass", "Mosenith", "Heang",
				"mosenith.heang@miu.edu", "admin");

		// create creditCard
		CreditCard creditCard1 = new CreditCard("MosenithHeang", 303, LocalDate.now());

		customer1.addCreditCard(creditCard1);
		creditCard1.setCustomer(customer1);

		customerRepository.save(customer1);

	}

}
