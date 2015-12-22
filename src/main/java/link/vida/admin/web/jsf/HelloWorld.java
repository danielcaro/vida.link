/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

@ManagedBean
@SessionScoped
public class HelloWorld {

    private String firstName = "John";
    private String lastName = "Doe";

    @Inject
    private ServletContext ctx;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String showGreeting() {

        return "Hello !!! " + " " + firstName + " " + lastName + " !! !";
    }
}
