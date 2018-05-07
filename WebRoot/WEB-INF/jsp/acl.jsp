<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>权限管理</title>	
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/acln.css">  
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/acld.css">  
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/acls.css">  
	<link href="${pageContext.request.contextPath}/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
	<div >
		<div class="container pb30">
			<div class="clear-backend">
			<div class="avatar">
				<h3 class="help"></h3>
				<div class="col-md-3 col-sm-4">
					<a href="" target="_blank">
						<!--<img src="img/admin.png" alt="">-->
<!--                         <div class="fa fa-cog fa-spin fa-5x fa-fw margin-bottom"></div>-->
							 <i class="fa fa-cog fa-spin fa-5x fa-fw margin-bottom adhead"></i>
					</a>
				</div>
			</div>

			<!-- tab-menu -->
			<input type="radio" class="tab-1" name="tab" checked="checked">
			<span>添加角色</span><i class="fa fa-plus-circle"></i>

			<input type="radio" class="tab-2" name="tab">
			<span>角色管理</span><i class="fa fa-user"></i>

			<input type="radio" class="tab-3" name="tab">
			<span>添加权限</span><i class="fa fa-medium"></i>			

			<input type="radio" class="tab-4" name="tab">
			<span>权限管理</span><i class="fa fa-suitcase"></i>
			
			<input type="radio" class="tab-5" name="tab">
			<span>添加用户</span><i class="fa fa-sitemap"></i>
			
			<input type="radio" class="tab-6" name="tab">
			<span>用户管理</span><i class="fa fa-users"></i>
			
			<!--<input type="radio" class="tab-7" name="tab">
			<span>Photos</span><i class="fa fa-photo"></i>
			
			<input type="radio" class="tab-8" name="tab">
			<span>Analysis</span><i class="fa fa-line-chart"></i>
			
			<input type="radio" class="tab-9" name="tab">
			<span>Links</span><i class="fa fa-link"></i>
			
			<input type="radio" class="tab-10" name="tab">
			<span>Settings</span><i class="fa fa-cog"></i>-->

			<!-- tab-top-bar -->
			<div class="top-bar">
            <h3>
	权限管理				
					
					
				</h3>
				<!--<ul>
					<li>
						<a href="" title="Log Out">
							<i class="fa fa-sign-out"></i>
						</a>
					</li>
					<li>
						<a href="" title="Messages">
							<i class="fa fa-envelope"></i>
						</a>
					</li>
					<li>
						<a href="" title="Edit">
							<i class="fa fa-edit"></i>
						</a>
					</li>
				</ul>-->
			</div>

			<!-- tab-content -->
			<div class="tab-content">
				<section class="tab-item-1">					
                    	<from>
                        	<div>
                           		 <table class="artable">
                                      <tr>
                                        <th scope="col" class="addrole">角色名称：</th>
                                        <td scope="col"><input name="name" type="text" class="distext" /></td>
                                        <td scope="col">&nbsp;</td>
                                      </tr>
                                      <tr>
                                        <th class="addrole" >角色描述：</th>
                                        <td><input name="remark" type="text"  class="distext" /></td>
                                        <td>&nbsp;</td>
                                      </tr>
                                      <tr>
                                        <th class="addrole">是否开启：</th>
                                        <td><input name="status" type="radio" value="0"  class="status" />&nbsp;关闭&nbsp;<input name="status" type="radio" value="1" checked="checked" />&nbsp;开启</td>
                                        <td>&nbsp;</td>
                                      </tr>
                                      <tr>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><div align="left"><input id="setUpbtn" name="setUpbtn" class="inputbutton" type="submit" value="保存添加" class="arsub" /></div></td>
                                      </tr>
                                    </table>
                            </div>
                        	
                        </form>	                    
				</section>
				<section class="tab-item-2">
					<div id="tborder">
                            <table cellpadding="0" cellspacing="0" style="border:1px solid #6493c8; background-color:#FFFFFF; width:100%; border-collapse:collapse;">
                                <tr class="gridtitle" align="center" style="height:25px;">
                                    <th colspan="4" scope="col"><div style="width:99%; line-height:35px;">角色列表</div></th>
                                </tr>
                                <tr class="">
                                    <td style="width:20%"><div align="center"><strong>序号</strong></div></td>
                                    <td style="width:30%"><div align="center"><strong>角色名称</strong></div></td>
                                    <td style="width:20%"><div align="center"><strong>开启状态</strong></div></td>
                                    <td style="width:30%"><div align="center"><strong>操作</strong></div></td>
                                </tr>
                                <tr class="">
                                        <td><div align="center">1</div></td>
                                        <td><div align="center">超级管理员</div></td>
                                        <td><div align="center">开启</div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/access/rid/1.html">配置权限</a></div></td>
                                    </tr><tr class="">
                                        <td><div align="center">2</div></td>
                                        <td><div align="center">管理员</div></td>
                                        <td><div align="center">开启</div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/access/rid/2.html">配置权限</a></div></td>
                                    </tr><tr class="">
                                        <td><div align="center">3</div></td>
                                        <td><div align="center">会员</div></td>
                                        <td><div align="center">开启</div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/access/rid/3.html">配置权限</a></div></td>
                                    </tr>      </table>
                    </div>

				</section>
				<section class="tab-item-3">
					<form action="/StudyimRBAC/index.php/rbac/addNodeHandle.html" method="post">
                        <div id="tborder">
                                <table cellpadding="0" cellspacing="0" style="border:1px solid #6493c8; background-color:#FFFFFF; width:100%; border-collapse:collapse;">
                                    <tr class="tdbk">
                                        <td width="20%"><div align="right"><strong>英文名称：</strong></div></td>
                                        <td><input name="name" class="mgleft" type="text" value="" onmouseover="ExtendedStyle('mouseover',this)" onmouseout="ExtendedStyle('mouseout',this)" onfocus="ExtendedStyle('focus',this)" onblur="ExtendedStyle('blur',this)" /></td>
                                    </tr>
                                    <tr class="tdbk">
                                        <td><div align="right"><strong>显示中文名称：</strong></div></td>
                                        <td><input name="title" class="mgleft" type="text" value="" onmouseover="ExtendedStyle('mouseover',this)" onmouseout="ExtendedStyle('mouseout',this)" onfocus="ExtendedStyle('focus',this)" onblur="ExtendedStyle('blur',this)" /></td>
                                    </tr>
                                    <tr class="tdbk">
                                        <td><div align="right"><strong>状态：</strong></div></td>
                                        <td><input name="status" type="radio" value="0" class="mgleft"  />&nbsp;禁用&nbsp;<input name="status" type="radio" value="1" checked="checked" class="mgleft"  />&nbsp;启用</td>
                                    </tr>
                                     <tr class="tdbk">
                                        <td><div align="right"><strong>类型：</strong></div></td>
                                        <td>
                                            <select id="level" name="level" class="mgleft" >
                                                    <option value="1">项目</option>
                                                    <option value="2">模块</option>
                                                    <option value="3">操作</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr class="tdbk">
                                        <td><div align="right"><strong>父节点：</strong></div></td>
                                        <td>
                                            <select name="pid" class="mgleft">
                                                <option value="0">根节点</option>
                                                <option value="1">后台管理</option><option value="2">　| 权限管理</option><option value="7">　| 文章管理</option><option value="16">　| 安全处理</option>		                	</select>
                                        </td>
                                    </tr>
                                    <tr class="tdbk">
                                        <td><div align="right"><strong>排序：</strong></div></td>
                                        <td><input name="sort" class="mgleft" type="text" value="" onmouseover="ExtendedStyle('mouseover',this)" onmouseout="ExtendedStyle('mouseout',this)" onfocus="ExtendedStyle('focus',this)" onblur="ExtendedStyle('blur',this)" /></td>
                                    </tr>
                                    <tr class="tdbk">
                                        <td colspan="2"><div align="center">
                                            <input id="setUpbtn" name="setUpbtn" class="inputbutton" type="submit" value="保存添加" onmouseover="ButtonStyle('mouseover',this)" onmouseout="ButtonStyle('mouseout',this)" /></div>
                                        </td>
                                    </tr>
                              </table>
                        </div>
                    </form>

				</section>
				<section class="tab-item-4">
					<div id="tborder">
                            <table cellpadding="0" cellspacing="0" style="border:1px solid #6493c8; background-color:#FFFFFF; width:100%; border-collapse:collapse;">
                                <tr class="gridtitle" align="center" style="height:25px;">
                                    <th colspan="6" scope="col"><div style="width:99%; line-height:24px;">权限管理</div></th>
                                </tr>
                                <tr class="tdbg">
                                    <td style="width:15%"><div align="center"><strong>权限ID</strong></div></td>
                                    <td style="width:20%"><div align="center"><strong>权限结构</strong></div></td>
                                    <td style="width:20%"><div align="center"><strong>名称</strong></div></td>
                                    <td style="width:15%"><div align="center"><strong>排序</strong></div></td>
                                    <td style="width:15%"><div align="center"><strong>类型</strong></div></td>
                                    <td style="width:15%"><div align="center"><strong>操作</strong></div></td>
                                </tr>
                                <tr class="tdbg">
                                        <td><div align="center">1</div></td>
                                        <td>
                                                <p style="text-indent:20px;"><label name="name" value="">·后台管理</label></p>
                                        </td>
                                        <td><div align="center">Home</div></td>
                                        <td><div align="center">1</div></td>
                                        <td><div align="center">项目</div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/1.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">2</div></td>
                                        <td>
                                                <p style="text-indent:40px;"><label name="name" value="">·权限管理</label></p>
                                        </td>
                                        <td><div align="center">Rbac</div></td>
                                        <td><div align="center">1</div></td>
                                        <td><div align="center"><div style="color:green;">模块</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/2.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">3</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·添加角色</label></p>
                                        </td>
                                        <td><div align="center">addRole</div></td>
                                        <td><div align="center">1</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/3.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">4</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·角色管理</label></p>
                                        </td>
                                        <td><div align="center">roleList</div></td>
                                        <td><div align="center">2</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/4.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">12</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·添加权限</label></p>
                                        </td>
                                        <td><div align="center">addNode</div></td>
                                        <td><div align="center">3</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/12.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">13</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·权限管理</label></p>
                                        </td>
                                        <td><div align="center">nodeList</div></td>
                                        <td><div align="center">4</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/13.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">14</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·添加用户</label></p>
                                        </td>
                                        <td><div align="center">adduser</div></td>
                                        <td><div align="center">5</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/14.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">15</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·用户管理</label></p>
                                        </td>
                                        <td><div align="center">userList</div></td>
                                        <td><div align="center">6</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/15.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">7</div></td>
                                        <td>
                                                <p style="text-indent:40px;"><label name="name" value="">·文章管理</label></p>
                                        </td>
                                        <td><div align="center">Article</div></td>
                                        <td><div align="center">2</div></td>
                                        <td><div align="center"><div style="color:green;">模块</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/7.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">9</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·发布文章</label></p>
                                        </td>
                                        <td><div align="center">addArticle</div></td>
                                        <td><div align="center">1</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/9.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">10</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·删除文章</label></p>
                                        </td>
                                        <td><div align="center">deleteArticle</div></td>
                                        <td><div align="center">2</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/10.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">11</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·更新文章</label></p>
                                        </td>
                                        <td><div align="center">updateArticle</div></td>
                                        <td><div align="center">3</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/11.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">16</div></td>
                                        <td>
                                                <p style="text-indent:40px;"><label name="name" value="">·安全处理</label></p>
                                        </td>
                                        <td><div align="center">Exit</div></td>
                                        <td><div align="center">3</div></td>
                                        <td><div align="center"><div style="color:green;">模块</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/16.html">[ 删除 ]</a></div></td>
                                    </tr><tr class="tdbg">
                                        <td><div align="center">17</div></td>
                                        <td>
                                                <p style="text-indent:60px;"><label name="name" value="">·安全退出</label></p>
                                        </td>
                                        <td><div align="center">index</div></td>
                                        <td><div align="center">1</div></td>
                                        <td><div align="center"><div style="color:blue;">操作</div></div></td>
                                        <td><div align="center"><a href="/StudyimRBAC/index.php/rbac/deletenode/id/17.html">[ 删除 ]</a></div></td>
                                    </tr>      </table>
                        </div>

				</section>
				<section class="tab-item-5">
						<form action="/StudyimRBAC/index.php/rbac/addUserHandle.html" method="post">
                            <div id="tborder">
                                    <table cellpadding="0" cellspacing="0" style="border:1px solid #6493c8; background-color:#FFFFFF; width:100%; border-collapse:collapse;">
                                        <tr class="gridtitle" align="center" style="height:25px;">
                                            <th colspan="2" scope="col"><div style="width:99%; line-height:24px;">添加用户</div></th>
                                        </tr>
                                        <tr class="tdbg">
                                            <td width="20%"><div align="right"><strong>用户名：</strong></div></td>
                                            <td><input name="username" class="inputtext" type="text" value="" onmouseover="ExtendedStyle('mouseover',this)" onmouseout="ExtendedStyle('mouseout',this)" onfocus="ExtendedStyle('focus',this)" onblur="ExtendedStyle('blur',this)" /></td>
                                        </tr>
                                        <tr class="tdbg">
                                            <td><div align="right"><strong>密码：</strong></div></td>
                                            <td><input name="password" class="inputtext" type="password" value="" onmouseover="ExtendedStyle('mouseover',this)" onmouseout="ExtendedStyle('mouseout',this)" onfocus="ExtendedStyle('focus',this)" onblur="ExtendedStyle('blur',this)" /></td>
                                        </tr>
                                        <tr class="tdbg">
                                            <td><div align="right"><strong>所属用户组：</strong></div></td>
                                            <td>
                                                <select name="role_id">
                                                    <option value="1">超级管理员</option><option value="2">管理员</option><option value="3">会员</option>		                	</select>
                                            </td>
                                        </tr>
                                        <tr class="tdbg">
                                            <td colspan="2"><div align="center">
                                                <input id="setUpbtn" name="setUpbtn" class="inputbutton" type="submit" value="保存添加" onmouseover="ButtonStyle('mouseover',this)" onmouseout="ButtonStyle('mouseout',this)" /></div>
                                            </td>
                                        </tr>
                                  </table>
                            </div>
                        </form>

				</section>
				<section class="tab-item-6">
					<div id="tborder">
                        <table cellpadding="0" cellspacing="0" style="border:1px solid #6493c8; background-color:#FFFFFF; width:100%; border-collapse:collapse;">
                            <tr class="gridtitle" align="center" style="height:25px;">
                                <th colspan="5" scope="col"><div style="width:99%; line-height:24px;">用户列表</div></th>
                            </tr>
                            <tr class="tdbg">
                                <td style="width:20%"><div align="center"><strong>序号</strong></div></td>
                                <td style="width:20%"><div align="center"><strong>用户名</strong></div></td>
                                <td style="width:20%"><div align="center"><strong>上次登录时间</strong></div></td>
                                <td style="width:20%"><div align="center"><strong>上次登录IP</strong></div></td>
                                <td style="width:20%"><div align="center"><strong>所属用户组</strong></div></td>
                            </tr>
                            <tr class="tdbg">
                                    <td><div align="center">3</div></td>
                                    <td><div align="center">studyim</div></td>
                                    <td><div align="center">2013-07-13 09:45:09</div></td>
                                    <td><div align="center">127.0.0.1</div></td>
                                    <td><div align="center">管理员</div></td>
                                </tr><tr class="tdbg">
                                    <td><div align="center">4</div></td>
                                    <td><div align="center">客音</div></td>
                                    <td><div align="center">2013-07-13 09:45:27</div></td>
                                    <td><div align="center">127.0.0.1</div></td>
                                    <td><div align="center">外部用户</div></td>
                                </tr><tr class="tdbg">
                                    <td><div align="center">6</div></td>
                                    <td><div align="center">admin</div></td>
                                    <td><div align="center">2013-07-13 09:47:53</div></td>
                                    <td><div align="center">127.0.0.1</div></td>
                                    <td><div align="center">超级管理员</div></td>
                                </tr>      </table>
                </div>

				</section>
				<!--<section class="tab-item-7">
					<h1>Sever</h1>
				</section>
				<section class="tab-item-8">
					<h1>Eight</h1>
				</section>
				<section class="tab-item-9">
					<h1>Nine</h1>
				</section>
				<section class="tab-item-10">
					<h1>Ten</h1>
				</section>-->
			</div>
		</div>
	</div>
	
</body>
</html>