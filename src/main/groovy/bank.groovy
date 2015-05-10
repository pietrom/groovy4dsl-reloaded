class Customer {
  int id
  String name
}

class BankAccount {
  int id
  double balance
  Customer owner
  void credit (double deposit) {
    balance += deposit
  }
  String toString() {
    "BankAccount id ${id} owner ${owner.name} balance is ${balance}"
  }
}
customer = new Customer(id:1,name:"Aaron Anderson")
savings = new BankAccount(id:2, balance:0.00, owner:customer)

savings.credit 20.00
println savings 
