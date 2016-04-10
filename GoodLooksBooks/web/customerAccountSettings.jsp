<%-- 
    Document   : customerAccountSettings
    Author     : Kevin Young
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
 <title>Change Account Settings</title>
 <mytags:header/>
</head>
<body>
 <mytags:customerNavbar/>
 <div class="glb-page">
   <mytags:logo/>
  <div class="container">
      <div class="panel panel-default">
          <div class="panel-heading">
              <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i>Change Account Settings</h3>
          </div>
              <form name="myForm" id = "signUpForm" class="form-horizontal signUpBody" action="" method="post">
              <div class="form-group">
                <label class = "control-label regLabel" for="username">Username:</label>
                <div class = "controls regControl">
                    <input type="text" class="form-control" name = "username">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="password">Password:</label>
                <div class = "controls regControl">
                    <input type="password" class="form-control" name = "password">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="firstName">First Name:</label>
                <div class = "controls regControl">
                    <input type="text" class="form-control" name="firstName">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="lastName">Last Name:</label>
                <div class = "controls regControl">
                    <input type="text" class="form-control" name ="lastName">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="street">Street:</label>
                <div class = "controls regControl">
                    <input type="text" class="form-control" name="street">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="city">City:</label>
                <div class = "controls regControl">
                    <input type="text" class="form-control" name="city">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="state">State:</label>
                <div class = "controls regControl">
                    <select name="state" class="form-control" name="state">
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
                    <input type="text" class="form-control" name="zipcode">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="telephone">Phone Number:</label>
                <div class = "controls regControl">
                    <input type="text" class="form-control input-medium bfh-phone" data-country="US" name="telephone" placeholder="0123456789">
                </div>
              </div>
              <div class="form-group">
                <label class = "control-label regLabel" for="email">Email:</label>
                <div class = "controls regControl">
                    <input type="email" class="form-control" name="email">
                </div>
              </div>
              <input id="Button1" class="btn btn-info" type="submit" value="Submit">
              <br>
      </div>
  </body>
  </html>
