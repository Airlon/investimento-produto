resource "aws_security_group" "investiment_product_sg" {
  name = "investiment_product_sg"
  description = "investiment_product security group"
  vpc_id = aws_vpc.investiment_product_vpc_1.id
}

resource "aws_security_group_rule" "sgr_pub" {
  from_port         = 0
  protocol          = "-1"
  security_group_id = aws_security_group.investiment_product_sg.id
  to_port           = 0
  type              = "egress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "sgr_ssh_id" {
  from_port         = 22
  protocol          = "tcp"
  security_group_id = aws_security_group.investiment_product_sg.id
  to_port           = 22
  type              = "ingress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "srg_https_in" {
  from_port         = 443
  protocol          = "tcp"
  security_group_id = aws_security_group.investiment_product_sg.id
  to_port           = 443
  type              = "ingress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_key_pair" "investiment_product_key" {
  key_name = "investiment_product_key"
  public_key = file("~/.ssh/...")
}
