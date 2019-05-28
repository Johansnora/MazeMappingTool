<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MazeMapper-Jnora</title>
</head>
<body style="font-family: monospace;">
<form method="post">
  <textarea name="maze" rows="30" cols="50">Type out Maze here!</textarea>
  <br>
    <input type="submit">
</form>
</br>
<tr><%= request.getAttribute("completedMaze") %></tr>
</body>
</html>