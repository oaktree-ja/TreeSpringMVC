<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글- 상세</title>
	</head>
	<style>
		table{
			border: 1px solid black;
			border-collapse: collapse;
		}
		
		th, td{
			border: 1px solid rgb(110,102,102);
		}
	</style>
	<body>
		<h1> 공지사항 - 상세</h1>
		
		<table>
			<tr>
				<th>제목</th>
				<td>${board.boardTitle }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.boardWriter }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${board.boardContent }
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><a href="../..${board.boardFilepath }" download>${board.boardFilename }</a></td>
			</tr>
		</table>
		<div>
			<button type= "button" onclick="showModifyForm(${board.boardNo });">수정하기</button>
			<button type= "button" onclick ="deleteConfirm(${board.boardNo});">삭제하기</button>
			<button type = "button" id="listBtn">목록으로</button>
			<button type= "button" onclick="goBack();">뒤로가기</button>
		</div>
		<script>
			function deleteConfirm(boardNo){
				var result = confirm("정말로 삭제하시겠습니까?");
				if(result){
					location.href="/board/delete/"+boardNo;
				}
			}
			function showModifyForm(boardNo){
				location.href="/board/modify/"+boardNo;
			}
			document.querySelector("#listBtn")
			.addEventListener("click",function(){
				location.href ="/board/list";
			});
			
			function goBack(){
				history.go(-1);
			}
		</script>
		
		
		
	</body>
</html>