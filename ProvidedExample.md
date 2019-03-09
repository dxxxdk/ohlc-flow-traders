# Provided example

## Input

```
1507000000 RESET
1507000001 ADD 1 B 10000 495
1507000002 ADD 2 S 10000 501
1507000031 ADD 3 B 5000 480
1507000050 ADD 4 B 600 499
1507000051 ADD 5 B 200 501
1507000053 ADD 6 S 800 495
1507000301 CANCEL 1
1507000302 MODIFY 2 3000 479
1507000303 MODIFY 3 5000 470
1507000304 ADD 7 S 4000 481
```

## Output

```
1506999900 498 500 498 498
1507000200 498 498 475 475
```

## Steps

### First interval

**OHCL: NaN**

#### `1507000000 RESET`

**OHCL: NaN**

#### `1507000001 ADD 1 B 10000 495`

**OHCL: NaN**

Buy orders

| ID    | Size  | Price |
| ----- | ----- | ----- |
| **1** | 10000 | 495   |

Sell orders

| ID  | Size | Price |
| --- | ---- | ----- |

#### `1507000002 ADD 2 S 10000 501`

**OHCL: 498** (open, low)

Buy orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 1   | 10000 | 495   |

Sell orders

| ID    | Size  | Price |
| ----- | ----- | ----- |
| **2** | 10000 | 501   |

#### `1507000031 ADD 3 B 5000 480`

**OHCL: 498**

Buy orders

| ID    | Size  | Price |
| ----- | ----- | ----- |
| 1     | 10000 | 495   |
| **3** | 5000  | 480   |

Sell orders

| ID  | Size | Price |
| --- | ---- | ----- |
| 2   | 10000 | 501   |

#### `1507000050 ADD 4 B 600 499`

**OHCL: 500** (high)

Buy orders

| ID    | Size  | Price |
| ----- | ----- | ----- |
| **4** | 600   | 499   |
| 1     | 10000 | 495   |
| 3     | 5000  | 480   |

Sell orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 2   | 10000 | 501   |

#### `1507000051 ADD 5 B 200 501`

**OHCL: 500**

Buy orders

| ID    | Size  | Price |
| ----- | ----- | ----- |
| **5** | 200   | 501   |
| 4     | 600   | 499   |
| 1     | 10000 | 495   |
| 3     | 5000  | 480   |

Sell orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 2   | 10000 | 501   |

**After transaction:**

Buy orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 4   | 600   | 499   |
| 1   | 10000 | 495   |
| 3   | 5000  | 480   |

Sell orders

| ID  | Size | Price |
| --- | ---- | ----- |
| 2   | 9800 | 501   |

#### `1507000053 ADD 6 S 800 495`

**OHCL: 498** (close)

Buy orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 5   | 200   | 501   |
| 4   | 600   | 499   |
| 1   | 10000 | 495   |
| 3   | 5000  | 480   |

Sell orders

| ID    | Size  | Price |
| ----- | ----- | ----- |
| **6** | 800   | 495   |
| 2     | 10000 | 501   |

**After transactions:**

Buy orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 1   | 10000 | 495   |
| 3   | 5000  | 480   |

Sell orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 2   | 10000 | 501   |

### Second interval

**OHCL: 498** (open, high)

#### `1507000301 CANCEL 1`

**OHCL: 490**

Buy orders

| ID  | Size | Price |
| --- | ---- | ----- |
| 3   | 5000 | 480   |

Sell orders

| ID  | Size  | Price |
| --- | ----- | ----- |
| 2   | 10000 | 501   |

#### `1507000302 MODIFY 2 3000 479`

**OHCL: Nan**

Buy orders

| ID  | Size | Price |
| --- | ---- | ----- |
| 3   | 5000 | 480   |

Sell orders

| ID    | Size | Price |
| ----- | ---- | ----- |
| **2** | 3000 | 479   |

**After transaction:**

Buy orders

| ID  | Size | Price |
| --- | ---- | ----- |
| 3   | 2000 | 480   |

Sell orders

| ID  | Size  | Price |
| --- | ----- | ----- |

#### `1507000303 MODIFY 3 5000 470`

**OHCL: Nan**

Buy orders

| ID    | Size  | Price |
| ----- | ----- | ----- |
| **3** | 5000  | 470   |

Sell orders

| ID  | Size  | Price |
| --- | ----- | ----- |

#### `1507000304 ADD 7 S 4000 481`

**OHCL: 475** (close, low)

Buy orders

| ID  | Size | Price |
| --- | ---- | ----- |
| 3   | 5000 | 470   |

Sell orders

| ID    | Size | Price |
| ----- | ---- | ----- |
| **7** | 4000 | 481   |