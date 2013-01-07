<html>
<body>

<h1>Add task</h1> 



<form action="/servlet/addTask" method="post">
    <table>
	      <tr>
	          <td>Task Name</td>
	          <td><input type="text" name="name" placeholder="Task Name" /></td>
        </tr>
        <tr>
            <td>What question would you like to ask the crowd?</td>
            <td><input type="text" name="question" placeholder="question" /></td>
        </tr>
        <tr>
            <td>To what accuracy do you want the subtasks answered?</td>
            <td><input type="number" name="accuracy" step="any" placeholder="between 0 and 1 e.g. 0.7" /></td>
        </tr>
        
        <tr>
            <td>What type of media are you uploading?</td>
            <td><select name="media_type">
            <option value="1">Image</option>
            <option value="2">Audio</option>
            <option value="3">Text</option>
            </select></td>
        </tr>
        <tr>
            <td>What type of annotation?</td>
            <td><select name="annotation_type">
            <option value="1">Binary</option>
            <option value="2">Category</option>
            <option value="3">NContinuous</option>
            </select></td>
        </tr>
        <tr>
            <td>answer 1?</td>
            <td><input type="text" name="answer1" /></td>
        </tr>
        <tr>
            <td>answer 2?</td>
            <td><input type="text" name="answer2" /></td>
        </tr>
        <tr>
            <td>What type of response do you expect?</td>
            <td><select name="input_type">
            <option value="1">Radio</option>
            <option value="2">Slider</option>
            <option value="3">Coordinates</option>
            <option value="4">Bounding Box</option>
            </select></td>
        </tr>
        </tr>
        <!--<tr>
            <td>What type of response do you expect?</td>
            <td><input type="text" name="question" value="binary" /></td>
        </tr>
        <tr>
            <td>What type of response do you expect?</td>
            <td><input type="text" name="question" placeholder="question" /></td>
        </tr>
        <tr>
            <td>What type of response do you expect?</td>
            <td><input type="text" name="question" placeholder="question" /></td>
        </tr>
        -->
        <tr>
            <td>What is the maximum number of responses you'd like for each subtask?</td>
            <td><input type="number" name="max_labels" value="15" /></td>
        </tr>
        <tr>
            <td>Expiry Day</td>
            <td><input type="number" name="day" min="1" max="31" /></td>
        </tr>
        <tr>
            <td>Expiry Month</td>
            <td><input type="number" name="month" min="1" max="12" /></td>
        </tr>
        <tr>
            <td>Expiry Year</td>
            <td><input type="number" name="year" min="2013" max="2015" /></td>
        </tr>
        <tr><td /><td><input type="submit" /></td></tr>
    </table>
</form>

</body>
</html>
