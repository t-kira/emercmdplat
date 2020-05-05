<!DOCTYPE html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
  <title>pdf</title>
  <style>
    body {
      position:relative;
        font-family:SimSun
    }
    .home {
      border:1px solid #ccc;
      padding:10px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
    .item {
      margin:20px
    }
    .item>span:first-child{
      margin-right:50px
    }
    .item>span:last-child{
      color:red
    }
    .list {
      display:flex
    }
  </style>
</head>

<body>
  <div id="app" class="home">
      <div class="item">
          <span>上报标题:</span>
          <span>${title}</span>
      </div>
      <div class="item">
          <span>上报内容:</span>
          <span>${content}</span>
      </div>
      <div class="list">
        <div class="item">
          <span>主送:</span>
          <span>李局长</span>
        </div>
        <div class="item">
          <span>抄送:</span>
          <span>李局长</span>
        </div>
        <div class="item">
          <span>报送:</span>
          <span>李局长</span>
        </div>
        <div class="item">
          <span>编辑:</span>
          <span>李局长</span>
        </div>
        <div class="item">
          <span>签发人:</span>
          <span>李局长</span>
        </div>
      </div>
      <div class="list">
        <div class="item">
          <span>联系电话:</span>
          <span>13298884444</span>
        </div>
        <div class="item">
          <span>签发时间:</span>
          <span>${issueTime}</span>
        </div>
      </div>
  </div>
</body>

</html>