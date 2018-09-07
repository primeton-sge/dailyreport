import json
import http.cookiejar
from smtpd import usage

import requests
from urllib import request,parse
import time
import sys, getopt

id = ''
password = ''
projectid = ''
taskid = ''
content = ''
date = ''
actHour=''
otwHour=''

cj = http.cookiejar.CookieJar()                                                     #声明一个CookieJar对象实例来保存cookie
opener = request.build_opener(request.HTTPCookieProcessor(cj), request.HTTPHandler) #利用urllib库的HTTPCookieProcessor对象来创建cookie处理器
request.install_opener(opener)                                                      #通过handler来构建opener

url = 'http://ame.primeton.com/default/ame/clipview/index.jsp' #servic起始地址
header = {
    'Host': 'ame.primeton.com',
    'Connection': 'keep-alive',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36',
    'Upgrade-Insecure-Requests': '1',
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
    'Accept-Encoding': 'gzip, deflate',
    'Accept-Language': 'zh-CN,zh;q=0.9',
    'Upgrade-Insecure-Requests': '1',
}
print('向',url,'发送请求...')
html = requests.get(url,headers = header, stream = True) #重定向后的页面
print('请求完成，获取重定向后的地址')
print('绑定cookie')

header['Cache-Control'] = 'max-age = 0'
header['Content-Type'] = 'application/x-www-form-urlencoded'
header['Referer'] = html.url
header['Origin'] = 'Origin: http://ame.primeton.com'



post = {
    '_eosFlowAction':'login',
    'loginPage':'ame/login/login.jsp',
    'password':password,
    'service':url,
    'username':id,
    'flag':'true',
}
print('向重定向地址',html.url,'发送登陆请求...')
postData = parse.urlencode(post).encode(encoding='utf-8')
req = request.Request(url = html.url,data = postData,headers = header)
res = request.urlopen(req)
res = res.read()
#print(res.decode(encoding='utf-8'))


url = 'http://ame.primeton.com/default/ame_common/wxworktime/com.primeton.rdmgr.labor.input.rdlabordetailbiz.saveAllRdLaborDetails1.biz.ext'
print('向地址',url,'发送填报工时请求...')
header.pop('Upgrade-Insecure-Requests')
header['Referer'] = url
header['X-Requested-With'] = 'XMLHttpRequest'
header['Content-Type'] = 'text/json'

post = {
    'insertEntities':[{
        'actHours':actHour,
        'userId': id,
        'laborDetailId':'',
        'laborDate':date,
        'otwHours':otwHour,
        'custid':3594,
        'projectId':projectid,
        'tasklist':taskid,
        'repContent':content,
        'status':'0',
        'userOrgId':360502,
        'isDaysOff':'0',
        'tbly':'',
        'omOrganization':{
            'orgid':360207
        }
    }]
}

postData = json.dumps(post).encode(encoding='utf-8')
req = request.Request(url = url,data = postData,headers = header)
res = request.urlopen(req)
res = res.read()
#print(res.decode(encoding='utf-8'))
