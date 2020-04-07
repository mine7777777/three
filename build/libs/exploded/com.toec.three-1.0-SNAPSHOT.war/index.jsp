<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta charset=utf-8>
    <title>My first three.js app</title>
    <style>

        .hide{
            display: none;
        }
        .c1{
            position: fixed;
            top:0;
            bottom: 0;
            left:0;
            right: 0;
            background: rgba(0,0,0,.5);
            z-index: 2;
        }
        .c2{
            background-color: white;
            position: fixed;
            width: 400px;
            height: 300px;
            top:50%;
            left: 50%;
            z-index: 3;
            margin-top: -150px;
            margin-left: -200px;
        }
        #modal p {
            margin-left:80px;
        }


        body {
            margin: 0;
        }

        canvas {
            width: 100%;
            height: 100%
        }

        .canvas_frame {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
        .toolbar {
            position: absolute;
            top: 10px;
            left: 10px;
            width: 25px;
        }
        .orderbar {
            position: absolute;
            top: 50px;
            right: 220px;
            width: 50px;
        }
        .mytext {
            height:40px;
            width:60px;
            text-align:center;
            line-height:100%;
            padding:0.1em;
           /* font:16px Arial,sans-serif bold;*/
            font-style:normal;
            text-decoration:none;
            margin:1px;
            vertical-align:text-bottom;
            zoom:1;
            outline:none;
            font-size-adjust:none;
            font-stretch:normal;
            border-radius:10px;
            box-shadow:0px 1px 2px rgba(0,0,0,0.2);
            text-shadow:0px 1px 1px rgba(0,0,0,0.3);
            /*color:#fefee9;*/
            border:0.2px solid #2299ff;
            background-repeat:repeat;
            background-size:auto;
            background-origin:padding-box;
            background-clip:padding-box;
            /*background-color:#3399ff;*/
            /*background: linear-gradient(to bottom, #eeeff9 0%,#3399ff 100%);*/
        }

        .text_pcinfos {
            height:340px;
            width: 220px;
            text-align:center;
            line-height:100%;
            padding:0.1em;
            /* font:16px Arial,sans-serif bold;*/
            font-style:normal;
            text-decoration:none;
            margin:1px;
            vertical-align:text-bottom;
            zoom:1;
            outline:none;
            font-size-adjust:none;
            font-stretch:normal;
            border-radius:10px;
            box-shadow:0px 1px 2px rgba(0,0,0,0.2);
            text-shadow:0px 1px 1px rgba(0,0,0,0.3);
            /*color:#fefee9;*/
            border:0.2px solid #2299ff;
            background-repeat:repeat;
            background-size:auto;
            background-origin:padding-box;
            background-clip:padding-box;
            /*background-color:#3399ff;*/
            /*background: linear-gradient(to bottom, #eeeff9 0%,#3399ff 100%);*/
        }

    .mybutton {
      height:40px;
      width:80px;
      text-align:center;
      line-height:100%;
      padding:0.3em;
      font:16px Arial,sans-serif bold;
      font-style:normal;
      text-decoration:none;
      margin:2px;
      vertical-align:text-bottom;
      zoom:1;
      outline:none;
      font-size-adjust:none;
      font-stretch:normal;
      border-radius:50px;
      box-shadow:0px 1px 2px rgba(0,0,0,0.2);
      text-shadow:0px 1px 1px rgba(0,0,0,0.3);
      color:#fefee9;
      border:0.2px solid #2299ff;
      background-repeat:repeat;
      background-size:auto;
      background-origin:padding-box;
      background-clip:padding-box;
      background-color:#3399ff;
      background: linear-gradient(to bottom, #eeeff9 0%,#3399ff 100%);
    }
    .mybutton:hover {
      background: #268DFF;
    }
  </style>
  <script src="lib/jquery-3.2.1.js"></script>
  <script src="lib/three.js"></script>
  <script src="lib/stats.js"></script>
  <script src="lib/controls/OrbitControls.js"></script>
  <script src="lib/ThreeBSP.js"></script>
  <script src="lib/tweenjs.js"></script>
  <script src="js/msj3D.js"></script>
  <script src="js/data.js"></script>
  <script>
      function Show(){
          document.getElementById('shade').classList.remove('hide');
          document.getElementById('modal').classList.remove('hide');
      }
      function Hide(){
          document.getElementById('shade').classList.add('hide');
          document.getElementById('modal').classList.add('hide');
      }
  </script>
</head>

<body onload="threeStart();">

<div id="canvas-frame" class="canvas_frame">
    <button id="mypop" style="height:120px;width: 120px;display: none;position: absolute;top: 10px;left: 10px;" value="234234"/>
</div>

<div id="shade" class="c1 hide"></div>
<div id="modal" class="c2 hide">
    <p>用户：<input type="text" /></p>
    <p>密码：<input type="password" /></p>
    <p>
        <input type="button" value="确定">
        <input type="button" value="取消" onclick="Hide()">
    </p>
</div>

</body>

</html>