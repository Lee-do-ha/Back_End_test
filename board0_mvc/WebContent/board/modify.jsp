<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ssafy.board.dto.ArticleDto"%>

<%@ include file="/include/header.jsp" %>
<%
	ArticleDto articledto = (ArticleDto)request.getAttribute("articleDto");
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link href="/board/assets/css/app.css" rel="stylesheet" />
    <title>SSAFY</title>
  </head>
  <body>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글수정</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <form id="form-modify" method="POST" action="">
          <input type="hidden" name = "articleNo" value="<%= articledto.getArticleNo()%>">
            <div class="mb-3">
              <label for="userid" class="form-label">작성자 ID : </label>
              <input type="text" class="form-control" id="userid" name="userid" value="<%= articledto.getUserId() %>" readonly />
            </div>
            <div class="mb-3">
              <label for="subject" class="form-label">제목 : </label>
              <input type="text" class="form-control" id="subject" name="subject" value="<%= articledto.getSubject() %>" />
            </div>
            <div class="mb-3">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" name="content" rows="7">
			<%= articledto.getContent() %>
              </textarea>
            </div>
            <div class="col-auto text-center">
              <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3">
              	  글수정
              </button>
              <button type="button" id="btn-list" class="btn btn-outline-danger mb-3">
               	 목록으로이동...
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-modify");
          form.setAttribute("action", "<%= root %>/register");
          form.submit();
        }
      });
      
      document.querySelector("#btn-list").addEventListener("click", function () {
    	  let form = document.querySelector("#form-modify");
          form.setAttribute("action", "<%= root %>/list");
          form.submit();
      });
    </script>
  </body>
</html>
