<html>
<body>

<h1>Add task</h1> 



<form action="/servlet/addTask" method="post">
	<input type="text" name="name" placeholder="Task Name" /><br>
    <input type="text" name="question" placeholder="question" /> <br>
    accuracy:<input type="number" name="accuracy" placeholder"accuracy" /> <br>
    Type(binary?):<input type="number" name="type" placeholder="currently only 1"/> <br>
    Max repsonses per subtask:<input type="number" name="max_labels" value="15" /> <br>
    Day:<input type="number" name="day" min="1" max="31" />
    Month:<input type="number" name="month" min="1" max="12" />
    Year:<input type="number" name="year" min="2013"/> <br>
    <input type="submit" />
</form>

</body>
</html>
