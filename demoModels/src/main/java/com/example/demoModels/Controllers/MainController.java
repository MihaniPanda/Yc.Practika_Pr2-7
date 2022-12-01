package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Passport;
import com.example.demoModels.Repo.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demoModels.Repo.EmployeeRepository;
import com.example.demoModels.Models.Employee;
import com.example.demoModels.Repo.ProductRepository;
import com.example.demoModels.Models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
@Controller
public class MainController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PassportRepository passportRepository;
    //НАЧАЛЬНЫЕ СТРАНИЦЫ
    @GetMapping("/")
    public String employerMain(Model model)
    {
        Iterable<Employee> employers = employeeRepository.findAll();
        Iterable<Passport> passports = passportRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        model.addAttribute("employers",employers);
        model.addAttribute("passports", passports);
        return "Employee/MainPage";
    }

    @GetMapping("/products")
    public String productsPage(Model model)
    {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "Product/Products";
    }

    //ДОБАВЛЕНИЕ СОТРУДНИКА
    @GetMapping("/employee/add")
    public String employeeAddView(@ModelAttribute( "employers") Employee employee, Model model)
    {
        Iterable<Passport> passports = passportRepository.findAll();
        ArrayList<Passport> passportArrayList = new ArrayList<>();
        for (Passport pass: passports){
            if(pass.getEmployee()==null) {
                passportArrayList.add(pass);
            }
        }
        model.addAttribute("passports", passportArrayList);

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);


        return "Employee/EmployeeCreate";
    }

    @PostMapping("/employee/add")
    public String employeeAdd(@ModelAttribute("employers") @Valid Employee employee, BindingResult bindingResuit,
                              @RequestParam String number,String description)
    {
        employee.setPassport(passportRepository.findByNumber(number));
        employee.setEmployees(productRepository.findByDescription(description));
        if(bindingResuit.hasErrors()){return "Employee/EmployeeCreate";}

        employeeRepository.save(employee);
        return "redirect:/";
    }

    //ДОБАВЛЕНИЕ ПРОДУКТА
    @GetMapping("/product/add")
    public String prooductAddView(@ModelAttribute("products") Product product)
    {

        return "Product/ProductCreate";
    }

    @PostMapping("/product/add")
    public String productAdd(@ModelAttribute("products") @Valid Product product,
                             BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Product/ProductCreate";}
        productRepository.save(product);
        return "redirect:/products";
    }

    //ФИЛЬТРАЦИЯ
    @GetMapping("/employee/filter")
    public String employeeFilter(Model model)
    {
        return "Employee/MainPage";
    }

    @GetMapping("/product/filter")
    public String productFilter(Model model)
    {
        return "Product/Products";
    }

    //ФИЛЬТРАЦИЯ СОТРУДНИКА
    @PostMapping("filterContains/result")
    public String employeeResultContains(@RequestParam String firstname, Model model)
    {
        List<Employee> result = employeeRepository.findByFirstnameContains(firstname);
        model.addAttribute("employers", result);
        return "Employee/MainPage";
    }
    @PostMapping("filterAll/result")
    public String employeeResultAll(@RequestParam String firstname, Model model)
    {
        List<Employee> result = employeeRepository.findByFirstname(firstname);
        model.addAttribute("employers", result);
        return "Employee/MainPage";
    }

    //ФИЛЬТРАЦИЯ ПРОДУКТА
    @PostMapping("/product/filterContains/result")
    public String productResultContains(@RequestParam String nameproduct, Model model)
    {
        List<Product> result = productRepository.findByNameproductContains(nameproduct);
        model.addAttribute("products", result);
        return "Product/Products";
    }
    @PostMapping("/product/filterAll/result")
    public String productResultAll(@RequestParam String nameproduct, Model model)
    {
        List<Product> result = productRepository.findByNameproduct(nameproduct);
        model.addAttribute("products", result);
        return "Product/Products";
    }

    //ИЗМЕНЕНИЕ СОТРУДНИКА
    @GetMapping("/employee/{id}")
    public String getEmployeedata(@PathVariable(value = "id") long id,Model model)
    {
        Optional<Employee> employee = employeeRepository.findById(id);
        model.addAttribute("employee", employee.get());
        if(!employeeRepository.existsById(id)){
            return "redirect:/";
        }
        return "Employee/EmployeeRedact";
    }

    @PostMapping("/employee/remove/{id}")
    public String deleteEmployeedata(@PathVariable(value = "id") long id,Model model)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployeedataPage(@PathVariable(value = "id") long id,Model model)
    {

        Employee res = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", res);
        if(!employeeRepository.existsById(id)){
            return "redirect:/";
        }
        return "Employee/EmployeeRedact";
    }

    @PostMapping("/employee/edit/{id}")
    public String editEmployedata(
            @PathVariable(value = "id") long id,
            @ModelAttribute("employee")  @Valid Employee employee, BindingResult bindingResult
   )
    {
        if(bindingResult.hasErrors()) return "Employee/EmployeeRedact";
        employeeRepository.save(employee);
        return "redirect:/";
    }

    //ИЗМИНЕНИЕ ПРОДУКТА
    @GetMapping("/product/{id}")
    public String getProductdata(@PathVariable(value = "id") long id,Model model)
    {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.get());
        if(!productRepository.existsById(id)){
            return "redirect:/products";
        }
        return "Product/ProductRedact";
    }

    @PostMapping("/product/{id}/remove/")
    public String deleteProductdata(@PathVariable(value = "id") long id,Model model)
    {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/products";
    }

    @GetMapping("/product/edit/{id}")
    public String editProductdataPage(@PathVariable(value = "id") long id,Model model)
    {

        Product res = productRepository.findById(id).orElseThrow();
        model.addAttribute("product",res);
        if(!productRepository.existsById(id)){
            return "redirect:/products";
        }
        return "Product/ProductRedact";
    }

    @PostMapping("/product/edit/{id}")
    public String editProductdata(@PathVariable(value = "id") long id,
                                  @ModelAttribute("product")  @Valid Product product, BindingResult bindingResult)

    {
        if(bindingResult.hasErrors()) return "Product/ProductRedact";
        productRepository.save(product);
        return "redirect:/products";
    }
}