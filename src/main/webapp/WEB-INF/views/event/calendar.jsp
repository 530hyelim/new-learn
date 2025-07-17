<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH)+1;
	int today = calendar.get(Calendar.DATE);
	
	calendar.set(year, month-1, 1);
	int startDay = calendar.get(Calendar.DAY_OF_WEEK);
	int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
%>
<h3><%= year %>년 <%= month %>월</h3>
<table>
	<tr>
		<th class="sunday">일</th>
		<th class="monday">월</th>
		<th class="tuesday">화</th>
		<th class="wednesday">수</th>
		<th class="thursday">목</th>
		<th class="friday">금</th>
		<th class="saturday">토</th>
	</tr>
	<tr><!-- 반복문으로 날짜 생성 -->
		<%
			int cellCount = 0;
			for (int i = 1; i < startDay; i++) {
				out.print("<td></td>");
				cellCount++;
			}
			for (int day = 1; day <= lastDay; day++) {
				if (cellCount % 7 == 0 && cellCount != 0) {
					out.print("<tr></tr>");
				}
				boolean isToday = (day == today);
				String dateStr = year + "-" + month + "-" + day;
		%>
				<td class="hover <%= isToday ? "today" : "" %>" 
				onclick="submitDate('<%= dateStr %>')">
		            <%= day %>
		        </td>
		<%
				cellCount++;
			}
		%>
	</tr>
</table>
<script>
	function submitDate(date) {
		const form = document.getElementById("dateForm");
		document.getElementById("selectedDate").value = date;
		form.submit();
	}
</script>