<%-- 
    Document   : accountSettings
    Author     : Kevin Young
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/header.jsp" />
    </head>
    <body>
        <c:choose>
            <c:when test="${empty user}">
                <jsp:include page="/guestNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'admin'}">
                <jsp:include page="/adminNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'librarian'}">
                <jsp:include page="/librarianNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'publisher'}">
                <jsp:include page="/publisherNavbar.jsp" />
            </c:when>
            <c:when test="${user.type == 'customer'}">
                <jsp:include page="/customerNavbar.jsp" />
            </c:when>
        </c:choose>
        <div class="glb-page">
            <jsp:include page="/logo.jsp" />
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Change Account Settings</h3>
                    </div>
                    <form name = "accountSettingsForm" action = "UpdateUserServlet" method = "post">
                        <div class="form-group">
                            <label class = "control-label regLabel" for="firstName">First Name:</label>
                            <div class = "controls regControl">
                                <input type="text" class="form-control" id="firstName" value="${user.firstName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class = "control-label regLabel" for="lastName">Last Name:</label>
                            <div class = "controls regControl">
                                <input type="text" class="form-control" id ="lastName" value="${user.lastName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class = "control-label regLabel" for="street">Street:</label>
                            <div class = "controls regControl">
                                <input type="text" class="form-control" id="street" value="${user.street}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class = "control-label regLabel" for="city">City:</label>
                            <div class = "controls regControl">
                                <input type="text" class="form-control" id="city" value="${user.city}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class = "control-label regLabel" for="state">State:</label>
                            <div class = "controls regControl">
                                <select name="state" class="form-control" id="state">
                                    <option value="AL">AL</option>
                                    <option value="AK">AK</option>
                                    <option value="AZ">AZ</option>
                                    <option value="AR">AR</option>
                                    <option value="CA">CA</option>
                                    <option value="CO">CO</option>
                                    <option value="CT">CT</option>
                                    <option value="DE">DE</option>
                                    <option value="DC">DC</option>
                                    <option value="FL">FL</option>
                                    <option value="GA">GA</option>
                                    <option value="HI">HI</option>
                                    <option value="ID">ID</option>
                                    <option value="IL">IL</option>
                                    <option value="IN">IN</option>
                                    <option value="IA">IA</option>
                                    <option value="KS">KS</option>
                                    <option value="KY">KY</option>
                                    <option value="LA">LA</option>
                                    <option value="ME">ME</option>
                                    <option value="MD">MD</option>
                                    <option value="MA">MA</option>
                                    <option value="MI">MI</option>
                                    <option value="MN">MN</option>
                                    <option value="MS">MS</option>
                                    <option value="MO">MO</option>
                                    <option value="MT">MT</option>
                                    <option value="NE">NE</option>
                                    <option value="NV">NV</option>
                                    <option value="NH">NH</option>
                                    <option value="NJ">NJ</option>
                                    <option value="NM">NM</option>
                                    <option value="NY">NY</option>
                                    <option value="NC">NC</option>
                                    <option value="ND">ND</option>
                                    <option value="OH">OH</option>
                                    <option value="OK">OK</option>
                                    <option value="OR">OR</option>
                                    <option value="PA">PA</option>
                                    <option value="RI">RI</option>
                                    <option value="SC">SC</option>
                                    <option value="SD">SD</option>
                                    <option value="TN">TN</option>
                                    <option value="TX">TX</option>
                                    <option value="UT">UT</option>
                                    <option value="VT">VT</option>
                                    <option value="VA">VA</option>
                                    <option value="WA">WA</option>
                                    <option value="WV">WV</option>
                                    <option value="WI">WI</option>
                                    <option value="WY">WY</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class = "control-label regLabel" for="zipcode">Zipcode:</label>
                            <div class = "controls regControl">
                                <input type="text" class="form-control" id="zipcode" value="${user.zipcode}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class = "control-label regLabel" for="phoneNumber">Phone Number:</label>
                            <div class = "controls regControl">
                                <input type="text" class="form-control input-medium bfh-phone" data-country="US" id="phoneNumber" value="${user.phoneNumber}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class = "control-label regLabel" for="email">Email:</label>
                            <div class = "controls regControl">
                                <input type="email" class="form-control" id="email" value="${user.email}">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class = "control-label regLabel">Accessibility</label>
                            <div class = "controls regControl">
                                
                                <c:choose>
                                    <c:when test="${user.dyslexic eq 'true'}">
                                        <input type="checkbox" name="dyslexic" value="true" checked="checked">>  Dyslexic<br>  
                                    </c:when>    
                                    <c:otherwise>
                                        <input type="checkbox" name="dyslexic" value="true">  Dyslexic<br> 
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${user.contrast eq 'true'}">
                                        <input type="checkbox" name="contrast" value="true" checked="checked">>  Contrast<br>  
                                    </c:when>    
                                    <c:otherwise>
                                        <input type="checkbox" name="contrast" value="true">  High Contrast<br> 
                                    </c:otherwise>
                                </c:choose>
                                
                            </div>
                        </div>
                        <input id="Button1" class="btn btn-info" type="submit" value="Submit">
                    </form>
                    <br>
                </div>
            </div>
        </div>
    </body>
</html>
