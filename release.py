#!/usr/bin/python
# coding=utf-8
import zipfile
import shutil
import os
import string

# 到发布路径
os.chdir('release')

# 空文件 便于写入此空文件到apk包中作为channel文件
src_empty_file = 'info/czt.txt'
# 创建一个空文件（不存在则创建）
f = open(src_empty_file, 'w')
f.close()

# 获取当前目录中所有的apk源包
src_apks = []
# python3 : os.listdir()即可，这里使用兼容Python2的os.listdir('.')
for file in os.listdir('.'):
    if os.path.isfile(file):
        extension = os.path.splitext(file)[1][1:]
        if extension in 'apk':
            src_apks.append(file)

# 获取渠道列表
# channel_file = 'info/channel.txt'
# f = open(channel_file)
# lines = f.readlines()
# f.close()

# 获取渠道列表
# default PPTV
channel_default = "s008";

# appstore channel
# 1-3
channel_list = [i for i in range(1, 4)];

# 5-8
for i in range(5, 9):
    channel_list.append(i);

# 12
channel_list.append(12);

# 17-20
for i in range(17, 21):
    channel_list.append(i);

# 54,57,58
channel_list.append(54);
channel_list.append(57);
channel_list.append(58);

# 小米
channel_list.append(82);

# 7004
channel_list.append(7004);

# 2372:PP手机渠道号
channel_list.append(2373);

# s001 vivo
channel_list.append("s001");

# s002 应用宝CPD
channel_list.append("s002");

# 头条信息流
channel_list.append("s003");
channel_list.append("s004");
channel_list.append("s005");
channel_list.append("s006");
channel_list.append("s007");

#渠道推广
channel_list.append("s009");

channel_list.append("s010");
channel_list.append("s012");

#小米市场投放包
channel_list.append("s016");
channel_list.append("s038");
channel_list.append("s039");
channel_list.append("s040");
channel_list.append("s041");
channel_list.append("s031");
channel_list.append("s032");



# meizu
channel_list.append(27)

# V2进行工信部备案:此渠道显示权限声明,还需在AppUtil.v2Channel中添加渠道
channel_list.append("sports_pre_v2")

for src_apk in src_apks:
    # file name (with extension)
    src_apk_file_name = os.path.basename(src_apk)
    # 分割文件名与后缀
    temp_list = os.path.splitext(src_apk_file_name)
    # name without extension
    src_apk_name = temp_list[0]
    # 后缀名，包含.   例如: ".apk "
    src_apk_extension = temp_list[1]

    # 创建生成目录
    output_dir = 'out/'
    # 目录不存在则创建
    if not os.path.exists(output_dir):
        os.mkdir(output_dir)

    # 设置pptv默认渠道
    target_channel = channel_default
    # 拼接对应渠道号的apk
    target_apk = output_dir + src_apk_name + "_" + target_channel + src_apk_extension
    # 拷贝建立新apk
    shutil.copy(src_apk, target_apk)
    # zip获取新建立的apk文件
    zipped = zipfile.ZipFile(target_apk, 'a', zipfile.ZIP_DEFLATED)
    # 初始化渠道信息
    empty_channel_file = "META-INF/cztchannel_{channel}".format(channel=target_channel)
    # 写入渠道信息
    zipped.write(src_empty_file, empty_channel_file)
    # 关闭zip流
    zipped.close()

    # 遍历渠道号并创建对应渠道号的apk文件
    for channel in channel_list:
        # 获取当前渠道号，因为从渠道文件中获得带有\n,所有strip一下
        target_channel = str(channel)
        # target_channel = "11"
        # 拼接对应渠道号的apk
        target_apk = output_dir + src_apk_name + "_" + target_channel + src_apk_extension
        # 拷贝建立新apk
        shutil.copy(src_apk, target_apk)
        # zip获取新建立的apk文件
        zipped = zipfile.ZipFile(target_apk, 'a', zipfile.ZIP_DEFLATED)
        # 初始化渠道信息
        empty_channel_file = "META-INF/cztchannel_{channel}".format(channel=target_channel)
        # 写入渠道信息
        zipped.write(src_empty_file, empty_channel_file)
        # 关闭zip流
        zipped.close()

os.chdir('..')
