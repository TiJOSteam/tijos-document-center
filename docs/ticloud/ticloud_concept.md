# 钛极云系统模型主要元素

## 产品

产品定义了在物联网系统中接入平台的设备类型， 包括名称、厂商、型号、设备标识等具体产品信息， 同时在产品中需定义该产品支持的数据点，即监控参数。 

产品标识用于在平台中唯一标识该产品，该标识用于物接入和解析，是物接入的重要组成部分。

## 设备

设备定义了在系统中具体接入到平台的实际设备，该设备必须属于某一种产品，包括名称、编号、分组及设备标识等信息。

设备标识与产品标识的组合在平台中唯一标识该设备， 该标识也用于物接入和解析，是系统的重要组成部分。

设备分组用于将设备进行归类管理。

## 数据点

数据点定义了与产品设备相关的具体监控参数， 如温度、压力等等， 由名称、单位、数据类型、数据点标识等组成， 是系统监控的基础。

数据点标识与设备标识、产品标识一起在平台中唯一标识被平台监控的参数， 是整个系统监控的最小单元，因此也是物接入的核心。 

## 报警

报警定义了设备或数据点在满足预定义的条件时产生报警记录提示用户或执行预定义的动作。报警由名称、触发条件、报警级别和描述组成。 

报警分为新建、恢复、确认多种状态用于标识该报警的当前具体情况， 当报警恢复并已人工确认后该报警将从报警列表中去除。

当数据从设备进入到平台时， 平台会根据触发条件进行判断是否产生新的报警或改变已有报警状态。

## 组态应用

组态是通过用户配置页面的方式直观展示设备状态并支持用户与设备进行交互， 组态页面基于HTML5， 可通过直观方式展示当前系统状态， 并可在PC，手机等支持浏览器的设备中实时展示。

## 日志

日志包括系统日志和设备日志， 系统日志记录了用户与系统的交互过程和结果， 设备日志记录了设备数据上报和命令下发的过程， 方便用户对设备的行为进行分析。

## 实时数据

实时数据反应了设备数据点的当前状态， 可在设备详情和组态页面中看到设备数据点的当前状态。

## 历史数据

历史数据记录了数据点所有历史数据， 可用于查看设备数据的变化趋势，并用于大数据分析。 

## OTA

设备应用空中升级