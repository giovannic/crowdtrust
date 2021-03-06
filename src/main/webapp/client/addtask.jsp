<html>
<head>
	<script type="text/javascript">
		function removeInput(input) {
			var type = document.getElementById("input_type");
			type.options.length = 0;
			var radio = document.createElement("option");
			radio.text = "Radio";
			radio.value = "RADIO";
			type.add(radio);
			var annotations = document.getElementById("annotation_type")
			annotations.options.length = 0;
			var	binary = document.createElement("option");
			binary.text = "Binary";
			binary.value = "BINARY";
			var multi = document.createElement("option");
			multi.text = "Category";
			multi.value = "MULTIVALUED";
			annotations.add(binary);
			annotations.add(multi);
			if(input == "IMAGE") {
				var cont = document.createElement("option");
				cont.text = "NContinuous";
				cont.value = "CONTINUOUS";
				annotations.add(cont);
			}
		}

		function changeAnnotation(annotation) {
			if(annotation == "BINARY") {
				binary();
			}
			if(annotation == "MULTIVALUED") {
				category();
			}
			if(annotation == "CONTINUOUS") {
				continuous();
			}
		}

		function displayAnswers(e) {
			var num = e.value;
			var i;
			for(i = 1; i <= num; i++) {
				var ans = "ans".concat(i);
				document.getElementById(ans).style.display = 'inline';
			}
			var j;
			for(j = 10; j > num; j--) {
				var ans = "ans".concat(j);
				document.getElementById(ans).style.display = 'none';
			}
		}

		

		function binary() {
			for(j = 10; j > 2; j--) {
				var ans = "ans".concat(j);
				document.getElementById(ans).style.display = 'none';
			}
			document.getElementById("numans").style.display = 'none';
			document.getElementById("ans1").style.display = 'inline';
			document.getElementById("ans2").style.display = 'inline';
		}

		function category() {
			document.getElementById("numans").style.display = 'inline';
			document.getElementById("ans1").style.display = 'none';
			document.getElementById("ans2").style.display = 'none';
		}

		function continuous() {
			var type = document.getElementById("input_type");
			type.options.length = 0;
			var boundingbox = document.createElement("option");
			boundingbox.text = "Bounding Box";
			boundingbox.value = "BOUNDING_BOX";
			type.add(boundingbox);
			document.getElementById("num_answers").value = 0;
			document.getElementById("numans").style.display = 'none';
			document.getElementById("ans1").style.display = 'none';
			document.getElementById("ans2").style.display = 'none';
			for(j = 10; j > 0; j--) {
				var ans = "ans".concat(j);
				document.getElementById(ans).style.display = 'none';
			}
		}
	</script>
</head>
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
            <td><input type="number" name="accuracy" step="any" placeholder="between 0 and 1 e.g. 0.7" min=0 max=1/></td>
        </tr>
				<tr>
            <td>What type of media are you uploading?</td>
            <td><select id="media_type" name="media_type" onchange="removeInput(this.value)">
            <option value="IMAGE">Image</option>
            <option value="AUDIO">Audio</option>
            <option value="VIDEO">Video</option>
            </select></td>
        </tr>
        <tr>
            <td>What type of annotation?</td>
            <td><select id="annotation_type" name="annotation_type" onchange="changeAnnotation(this.value);">
            <option value="BINARY">Binary</option>
            <option value="MULTIVALUED">Category</option>
            <option value="CONTINUOUS">NContinuous</option>
            </select></td>
        </tr>
				<tr id="numans" style="display:none;">
						<td>How many answers would you like?</td>
						<td><input type="number" id="num_answers" name="num_answers" placeholder="between 2 and 10" min=2 max=10 onchange="displayAnswers(this)" value="2" /></td>
				</tr>
        <tr id="ans1">
            <td>answer 1?</td>
            <td><input type="text" name="answer1" /></td>
        </tr>
        <tr id="ans2">
            <td>answer 2?</td>
            <td><input type="text" name="answer2" /></td>
        </tr>
				<tr id="ans3" style="display:none;">
            <td >answer 3?</td>
            <td><input type="text" name="answer3" /></td>
        </tr>
        <tr id="ans4" style="display:none;">
            <td>answer 4?</td>
            <td><input type="text" name="answer4" /></td>
        </tr>
				<tr id="ans5" style="display:none;">
            <td>answer 5?</td>
            <td><input type="text" name="answer5" /></td>
        </tr>
        <tr id="ans6" style="display:none;">
            <td>answer 6?</td>
            <td><input type="text" name="answer6" /></td>
        </tr>
				<tr id="ans7" style="display:none;">
            <td>answer 7?</td>
            <td><input type="text" name="answer7" /></td>
        </tr>
        <tr id="ans8" style="display:none;">
            <td>answer 8?</td>
            <td><input type="text" name="answer8" /></td>
        </tr>
				<tr id="ans9" style="display:none;">
            <td>answer 9?</td>
            <td><input type="text" name="answer9" /></td>
        </tr>
        <tr id="ans10" style="display:none;">
            <td>answer 10?</td>
            <td><input type="text" name="answer10" /></td>
        </tr>
        <tr>
            <td>What type of response do you expect?</td>
            <td><select id="input_type" name="input_type">
            <option value="RADIO">Radio</option>
            </select></td>
        </tr>
        <tr>
            <td>What is the maximum number of responses you'd like for each subtask?</td>
            <td><input type="number" name="max_labels" value="15" /></td>
        </tr>
        <tr>
            <td>Expiry Day</td>
            <td><input type="number" name="day" min=1 max=31 /></td>
        </tr>
        <tr>
            <td>Expiry Month</td>
            <td><input type="number" name="month" min=1 max=12 /></td>
        </tr>
        <tr>
            <td>Expiry Year</td>
            <td><input type="number" name="year" min=2013 max=2015 /></td>
        </tr>
        <tr><td /><td><input type="submit" /></td></tr>
    </table>
</form>

</body>
</html>
