data "aws_ami" "investiment_product_ami" {
  most_recent = true
  owners = ["000995655648654"]

  filter {
    name   = "name"
    values = ["ubuntu/images/......"]
  }
}