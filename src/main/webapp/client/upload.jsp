<html>
<body>

<h1>FILE UPLOAD</h1>



<form action="/servlet/upload" method="post" enctype="multipart/form-data">
	<input type="text" name="task" placeholder="Task" /><br>
    <input type="file" name="file" /><br>
    <input type="text" name="taskID" /> <br>
    <input type="submit" />
</form>

</body>
</html>
