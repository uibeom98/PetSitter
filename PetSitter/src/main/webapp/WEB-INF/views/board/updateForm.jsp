<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
	<form>
		<input type=hidden" id="id" value="${board.id}"/>
		<div class="form-group">
			<input value="${board.title}" type="text" class="form-control"
			 placeholder="Enter title" id="title">
		</div>
		
		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">수정</button>
<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 500
      });
</script>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

