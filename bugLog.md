## Bug 1
Brief description: Computer - the method setOS does not accurately set the new OS, instead it sets it to "None"
Failed unit test: test_setOS

## Bug 2
Brief description: Resale Shop - the method buy doesn't actually throw an exception if a computer is already in the inventory
Failed unit test: test_buyException

## Bug 3
Brief description: Resale Shop - the method buy overwrites the computer paramater that is supposed to be passed in 
Failed unit test: test_buy

## Bug 4
Brief description: Resale Shop - the method sell doesn't throw an exception if the computer is not in inventory
Failed unit test: test_sellException

## Bug 5
Brief description: Resale Shop - the loop in the method printn inventory has <=, which allows it to go one past the length of the array
Failed unit test: test_printInventoryOne

## Bug 6
Brief description: Computer - the constructor automatically makes every memory equal to 16
Failed unit test: test_computerConstructorMemory

## Bug 7
Brief description: Computer - the constructor automatically makes every price equal to 0
Failed unit test: test_computerConstructorPrice

## Bug 8
Brief description: Resale Shop - the constructor automatically creates a computer and adds it to inventory
Failed unit test: test_computerconstructorInventory

## Bug 9
Brief description:  Resale Shop - refurbish doesn't set the operating system to the parameter, sets it to none
Failed unit test: test_refurbish

## Bug 10
Brief description: Resale Shop - resale shop constructor should create an empty inventory upon initialization without adding a computer
Failed unit test: test_constructorInventory