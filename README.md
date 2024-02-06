## 一、简介

这个特效在 1000vs10 大逃杀活动中出现过，复原了一下

拖尾方块，时长，间隔都可自定义，故可以配置发光方块拖尾

由于是随意写的插件，并没有**专门**进行性能优化，大家可以自己测试

建议通过 CMI, Itemcommand 等插件将/escape绑定到道具上

![](https://plugin.sandtripper.cn/img/rainbow_escape_show.gif)

## 二、插件适用

#### 服务端核心

Bukkit、Spigot 以及其分支

Foila 不可用

#### 版本

1.13+

## 三、获取插件

[下载链接](https://github.com/SandTripper/RainbowEscape/releases/download/1.0/RainbowEscape-1.0.jar)

## 四、安装插件

直接将插件文件拖入plugins，可选择重启服务端，也可热加载

## 五、配置文件

```yaml
# 发射速度
speed: 2.5

# 方块持续tick数
tick-block-keep: 20

# 方块切换间隔：tick数
tick-block-interval: 1

# 特效持续时间：tick数
tick-effect-duration: 100

# 切换的材料，使用材料 id 的大写形式
materials:
  - 'RED_WOOL'
  - 'ORANGE_WOOL'
  - 'YELLOW_WOOL'
  - 'LIME_WOOL'
  - 'LIGHT_BLUE_WOOL'
  - 'BLUE_WOOL'
  - 'PURPLE_WOOL'
```

## 六、指令

* /escape 发射自己
* /escape reload 重载插件

## 七、权限

所有指令权限都是

rainbowescape.command

## 八、使用统计

![](https://bstats.org/signatures/bukkit/RainbowEscape.svg)