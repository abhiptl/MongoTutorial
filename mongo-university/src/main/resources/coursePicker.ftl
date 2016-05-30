<html>
  <head><title>Course Picker</title></head>
  <body>
     <form action="/favorite_course" method="POST">
        <p>What is your favorite Course?</p>
        <#list courses as course>
          <p>
             <input type="radio" name="course" value="${course}">${course}</input>
          </p>
        </#list>
        <input type="submit" value="Submit"/>
     </form>
  </body>
</html>
