<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="main">
	<div class="container">
		<form action="/DuyHao/myaccount" method="post" enctype="multipart/form-data">
			<c:choose>
				<c:when test="${sessionScope.account != null}">
					<div class="account-details">
						<h3>Xin chào, ${sessionScope.account.fullName}!</h3>
						<p>
							<strong>Full Name:</strong> ${sessionScope.account.fullName}
						</p>
						<p>
							<strong>Phone:</strong> ${sessionScope.account.phone}
						</p>
						<c:if test="${not empty sessionScope.account.avatar}">
							<p>
								<strong>Profile Image:</strong>
							</p>
							<img src="${pageContext.request.contextPath}/image?username=${sessionScope.account.avatar}" alt="Profile Image">
						</c:if>
						<!-- Input để upload hình ảnh -->
						<p>
							<strong>Upload New Profile Image:</strong> 
							<input type="file" name="profileImage" accept="image/*" required>
						</p>
						<!-- Nút gửi form -->
						<button type="submit" class="btn btn-primary">Cập nhật hình ảnh</button>
					</div>
				</c:when>
				<c:otherwise>
					<p>
						Bạn chưa đăng nhập. Vui lòng <a
							href="${pageContext.request.contextPath}/login">Đăng nhập</a>.
					</p>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</div>