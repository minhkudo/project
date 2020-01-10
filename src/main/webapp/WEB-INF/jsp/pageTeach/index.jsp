<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/css.jsp" %>
<%@ include file="include/menu.jsp" %>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Charts &amp; graphs</a></div>
        <h1>Charts &amp; graphs <span style="color: red">${result.message}</span></h1>
    </div>
    <%
       String a = (String) session.getAttribute("ad");
       System.out.println("a: "+a);
    %>
    <div class="container-fluid">
        <div class="widget-box widget-plain">
            <div class="center">
                <ul class="stat-boxes">
                    <li>
                        <div class="left peity_bar_neutral"><span><span style="display: none;">2,4,9,7,12,10,12</span>
                                <canvas width="50" height="24"></canvas>
                            </span>+10%</div>
                        <div class="right"> <strong>15598</strong> Visits </div>
                    </li>
                    <li>
                        <div class="left peity_line_neutral"><span><span style="display: none;">10,15,8,14,13,10,10,15</span>
                                <canvas width="50" height="24"></canvas>
                            </span>10%</div>
                        <div class="right"> <strong>150</strong> New Users </div>
                    </li>
                    <li>
                        <div class="left peity_bar_bad"><span><span style="display: none;">3,5,6,16,8,10,6</span>
                                <canvas width="50" height="24"></canvas>
                            </span>-40%</div>
                        <div class="right"> <strong>4560</strong> Orders</div>
                    </li>
                    <li>
                        <div class="left peity_line_good"><span><span style="display: none;">12,6,9,23,14,10,17</span>
                                <canvas width="50" height="24"></canvas>
                            </span>+60%</div>
                        <div class="right"> <strong>5672</strong> Active Users </div>
                    </li>
                    <li>
                        <div class="left peity_bar_good"><span>12,6,9,23,14,10,13</span>+30%</div>
                        <div class="right"> <strong>2560</strong> Register</div>
                    </li>
                    <li>
                        <div class="left peity_bar_bad"><span>12,6,9,23,14,10,5</span>-5%</div>
                        <div class="right"> <strong>155</strong> Panding Orders</div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>Bar chart</h5>
                    </div>
                    <div class="widget-content">
                        <div class="bars"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>Line chart</h5>
                    </div>
                    <div class="widget-content">
                        <div class="chart"></div>
                    </div>
                </div>
            </div>
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>Pie chart</h5>
                    </div>
                    <div class="widget-content">
                        <div class="pie"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>Different Bars</h5>
                    </div>
                    <div class="widget-content">
                        <div class="progress progress-info">
                            <div class="bar" style="width: 20%"></div>
                        </div>
                        <div class="progress progress-success">
                            <div class="bar" style="width: 40%"></div>
                        </div>
                        <div class="progress progress-warning">
                            <div class="bar" style="width: 60%"></div>
                        </div>
                        <div class="progress progress-danger">
                            <div class="bar" style="width: 80%"></div>
                        </div>
                        <pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"progress"</span><span class="tag">&gt;</span></li><li class="L1"><span class="pln"> </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"bar"</span><span class="pln"> </span><span class="atn">style</span><span class="pun">=</span><span class="atv">"</span><span class="pln">width</span><span class="pun">:</span><span class="pln"> </span><span class="lit">difine</span><span class="pun">%;</span><span class="atv">"</span><span class="tag">&gt;&lt;/div&gt;</span></li><li class="L2"><span class="tag">&lt;/div&gt;</span></li></ol>
                        </pre>
                        <div class="progress progress-striped">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <div class="progress progress-striped progress-success">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <div class="progress progress-striped progress-warning">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <div class="progress progress-striped progress-danger">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"progress progress-striped"</span><span class="tag">&gt;</span></li><li class="L1"><span class="pln"> </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"bar"</span><span class="pln"> </span><span class="atn">style</span><span class="pun">=</span><span class="atv">"</span><span class="pln">width</span><span class="pun">:</span><span class="pln"> </span><span class="lit">60</span><span class="pun">%;</span><span class="atv">"</span><span class="tag">&gt;&lt;/div&gt;</span></li><li class="L2"><span class="tag">&lt;/div&gt;</span></li></ol>
                        </pre>
                        <div class="progress progress-striped active">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <div class="progress progress-striped progress-success active">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <div class="progress progress-striped progress-warning active">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <div class="progress progress-striped progress-danger active">
                            <div class="bar" style="width: 60%;"></div>
                        </div>
                        <pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"progress progress-striped active"</span><span class="tag">&gt;</span></li><li class="L1"><span class="pln"> </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"bar"</span><span class="pln"> </span><span class="atn">style</span><span class="pun">=</span><span class="atv">"</span><span class="pln">width</span><span class="pun">:</span><span class="pln"> </span><span class="lit">60</span><span class="pun">%;</span><span class="atv">"</span><span class="tag">&gt;&lt;/div&gt;</span></li><li class="L2"><span class="tag">&lt;/div&gt;</span></li></ol>
                        </pre>
                        <div class="progress">
                            <div class="bar bar-success" style="width: 35%;"></div>
                            <div class="bar bar-warning" style="width: 20%;"></div>
                            <div class="bar bar-danger" style="width: 10%;"></div>
                        </div>
                        <pre class="prettyprint linenums"><ol class="linenums"><li class="L0"><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"progress"</span><span class="tag">&gt;</span></li><li class="L1"><span class="pln"> </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"bar bar-success"</span><span class="pln"> </span><span class="atn">style</span><span class="pun">=</span><span class="atv">"</span><span class="pln">width</span><span class="pun">:</span><span class="pln"> </span><span class="lit">35</span><span class="pun">%;</span><span class="atv">"</span><span class="tag">&gt;&lt;/div&gt;</span></li><li class="L2"><span class="pln"> </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"bar bar-warning"</span><span class="pln"> </span><span class="atn">style</span><span class="pun">=</span><span class="atv">"</span><span class="pln">width</span><span class="pun">:</span><span class="pln"> </span><span class="lit">20</span><span class="pun">%;</span><span class="atv">"</span><span class="tag">&gt;&lt;/div&gt;</span></li><li class="L3"><span class="pln"> </span><span class="tag">&lt;div</span><span class="pln"> </span><span class="atn">class</span><span class="pun">=</span><span class="atv">"bar bar-danger"</span><span class="pln"> </span><span class="atn">style</span><span class="pun">=</span><span class="atv">"</span><span class="pln">width</span><span class="pun">:</span><span class="pln"> </span><span class="lit">10</span><span class="pun">%;</span><span class="atv">"</span><span class="tag">&gt;&lt;/div&gt;</span></li><li class="L4"><span class="tag">&lt;/div&gt;</span></li></ol>
                        </pre>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="include/page.jsp" %>
<%@ include file="include/footer.jsp" %>
<script src="<c:url value='/js/excanvas.min.js'/>"></script>
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/jquery.ui.custom.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/jquery.flot.min.js'/>"></script>
<script src="<c:url value='/js/jquery.flot.pie.min.js'/>"></script>
<script src="<c:url value='/js/jquery.flot.resize.min.js'/>"></script>
<script src="<c:url value='/js/maruti.js'/>"></script>
<script src="<c:url value='/js/maruti.charts.js'/>"></script>
<script src="<c:url value='/js/maruti.dashboard.js'/>"></script>
<script src="<c:url value='/js/jquery.peity.min.js'/>"></script>
<script src="<c:url value='/js/fullcalendar.min.js'/>"></script>