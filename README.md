TransitRailMod
==============

A mod featuring platform doors and other elements of transit railway systems, now on minecraft `1.8`.

Unstable build at [my website](https://cth451.tk/transitrailmod-updates)

To-be-implemented List
======================

Item List Shown
---------------
* Style Changer `style_changer`
* Closed Platform Door `closed_platform_door_item`
* Closed Platform Panel `closed_platform_panel_item`
* Platform Panel `platform_panel_item`
* Platform Gate `platform_gate_item`
* Ticketing Gate `ticketing_gate_item`
* Ticket `ticket`
* Ticket Machine `ticket_machine_item`

Block List Shown
----------------
* Logo Block `logo_block`
* Hung Arrow Sign `hung_arrow_sign`
* Platform Sign `platform_sign`

Block List Hidden
-----------------
* Closed Platform Top Block `ClosedPlatformTop`

Properties: `FACING` (Inherited) `POWERED`

* Closed Platform Door `ClosedPlatformDoorBlock`

Properties: `FACING` (Inherited) `UPPER` `POWERED` `LEFT`

* Closed Platform Panel `ClosedPlatformPanelBlock`

Properties: `FACING` (Inherited) `UPPER`

* Platform Gate `PlatformGateBlock`

Properties: `FACING` (Inherited) `UPPER` `POWERED` `LEFT`

* Platform Panel `PlatformPanelBlock`

Properties: `FACING` (Inherited) `UPPER`

Abstract Block Class List
-------------------------
* `ClosedPlatformBlock`

Subclasses: `ClosedPlatformPanelBlock` `ClosedPlatformTop` `ClosedPlatformDoorBlock`

* `PlatformBlock`

Subclasses: `PlatformPanelBlock` `PlatformGateBlock`
