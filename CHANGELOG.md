Change Log
==========

0.9
---

Added:
* Turnstile Block
* Slim Passenger Detector Block
* Working Ticket Machine GUI

Changed:
* Train Ticket has maximum number of rides
* Recipes are handled more freely now

Removed:
* `ticketing_gate_item`

Minecraft will warn about missing items. Yet these things are not implemented previously and unavailable in survival. Just continue.

0.8
---

Added:
* Wire Panel Corner
* Wire Panel Extra model

Fixed:
* Issue #6: platform doors and panels breaking fix

Changed:
* The style changer now has damage values

0.7
---

Added:

* Fluorescent Lamp
* Wire Panel

Fixed:

* Blocks and Item in hand rendering
* Update platform gate bounding boxes on BlockState change
* `canHarvestBlock()` for Arrow Signs and some others are explicitly set to true
* Hung Arrow Sign texture error
* Platform gates and doors now have indicator lights

Optimized:

* Reduced `getActualState()` method call
* Utilized abstract blocks and items

0.6
---

Implemented:

* Hung Arrow Sign
* Platform Arrow Sign

Changed:

* Changed to white glass

Added crafting receipes:

* Closed Platform Door
* Closed Platform Panel
* Platform Panel
* Platform Gate 
* Logo Block
* Hung Arrow Sign
* Platform Arrow Sign

Known caveats

* Optifine renders platform panels and gates as opaque blocks

0.5
---

Initial stable testing release

Implemented item and block functions:

* Closed Platform Door
* Closed Platform Panel
* Platform Panel
* Platform Gate 

