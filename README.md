# Promotion Engine

## This is an implementation of a simple promotion engine for a checkout process. 
Our Cart contains a list of single character
SKU ids (A, B, C. ..) over which the promotion engine will need to run.
The promotion engine will calculate the total order value after applying the 2 promotion types <br/>
• buy 'n' items of a SKU for a fixed price (3 A's for 130)<br/>
• buy SKU 1 & SKU 2 for a fixed price ( C + D = 30 )

The promotion engine should be modular to allow for more promotion types to be added at a later date (e.g. a future promotion could be x% of a SKU unit price). For this coding exercise you can assume that the promotions will be mutually exclusive; in other words if one is applied the other promotions will not apply

## Test Setup
#### _Unit price for SKU IDs_<br/> 
A  &nbsp; 50<br/>
B  &nbsp; 30<br/>
C  &nbsp; 20<br/>
D  &nbsp; 15

#### _Active Promotions_
3 of A's &nbsp;for 130<br/>
2 of B's &nbsp;for 45<br/>
C & D &nbsp;for 30

#### _Scenario A_
1`*`A&nbsp; 50<br/>
1`*`B&nbsp; 30<br/>
1`*`C&nbsp; 20

Total&nbsp; 100

#### _Scenario B_
5 `*` A &nbsp;&nbsp;130 + 2`*`50<br />
5 `*` B &nbsp;&nbsp;45 + 45 + 30<br/>
1`*`C &nbsp;&nbsp;&nbsp;  28

Total&nbsp; 370

#### _Scenario C_
3`*`A &nbsp;&nbsp;130<br/>
5 `*` B &nbsp;45 + 45 + 1 `*` 30<br/>
1`*`C-<br/>
1`*`D&nbsp;&nbsp;&nbsp; 30

Total&nbsp; 280