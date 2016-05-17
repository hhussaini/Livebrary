<%-- 
    Document   : itemSettings
    Author     : Kevin Young
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <form name = "itemSettingsForm" action = "ItemSettingsServlet" method = "post" class="form-horizontal signUpBody">
      <div class="form-group">
         <h3><b>Lending periods</b></h3>
         <p>Set your lending period preference for each format.</p>
         <label for="eBookLendPeriod">eBook: ${user.eBookLendPeriod} Days</label>
         <div class = "controls regControl">
              <select name="eBookLendPeriod" class="form-control" id="eBookLendPeriod" name="eBookLendPeriod">
                 <option value="7">7 Days</option>
                 <option value="14">14 Days</option>
                 <option value="21">21 Days</option>
              </select>
         </div>
      </div>
      <div class="form-group">
         <label for="audiobookLendPeriod">Audiobook: ${user.audiobookLendPeriod} Days</label>
         <div class = "controls regControl">
              <select name="audiobookLendPeriod" class="form-control" id="audiobookLendPeriod" name="audiobookLendPeriod">
                 <option value="7">7 Days</option>
                 <option value="14">14 Days</option>
                 <option value="21">21 Days</option>
              </select>
         </div>
      </div>
      <div class="form-group">
         <label for="videoLendPeriod">Video: ${user.videoLendPeriod} Days</label>
         <div class = "controls regControl">
              <select name="videoLendPeriod" class="form-control" id="videoLendPeriod" name="videoLendPeriod">
                 <option value="7">7 Days</option>
                 <option value="14">14 Days</option>
                 <option value="21">21 Days</option>
              </select>
         </div>
      </div>
      <hr>
      <div class="form-group">
         <h3><b>Maturity level(s)</b></h3>
         <p>Choose the range of content you would like to see while browsing and searching the catalog.</p>
         <label for="maturityStart">Start: ${user.maturityStart}</label>
         <div class = "controls regControl">
              <select name="maturityStart" class="form-control" id="maturityStart" name="maturityStart">
                 <option value="Juvenile">Juvenile</option>
                 <option value="Young Adult">Young Adult</option>
                 <option value="General Adult">General Adult</option>
                 <option value="Mature Adult">Mature Adult</option>
              </select>
         </div>
      </div>
      <div class="form-group">
         <label for="maturityEnd">End: ${user.maturityEnd}</label>
         <div class = "controls regControl">
              <select name="maturityEnd" class="form-control" id="maturityEnd" name="maturityEnd">
                 <option value="Juvenile">Juvenile</option>
                 <option value="Young Adult">Young Adult</option>
                 <option value="General Adult">General Adult</option>
                 <option value="Mature Adult">Mature Adult</option>
              </select>
         </div>
      </div>
      <input type="submit" value="Submit">
   </form>
</html>