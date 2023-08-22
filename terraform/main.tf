resource "aws_vpc" "investiment_product_vpc_1" {
  cidr_block = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support = true

  tags = {
    "name" = "investiment_product_vpc_1"
  }
}

resource "aws_default_subnet" "investiment_product_subnet" {
  vpc_id = aws_vpc.investiment_product_vpc_1.id
  cidr_block = "10.0.0.0/24"
  availability_zone = "us-west-2a"
  map_public_ip_on_launch = true

  tags = {
    Name = "investiment_product_subnet"
  }
}

resource "aws_internet_gateway" "investiment_product_igw" {
  vpc_id = aws_vpc.investiment_product_vpc_1.id

  tags = {
    "Name" = "investiment_product_igw"
  }
}
resource "aws_route_table" "investiment_product_rtb" {
  vpc_id = aws_vpc.investiment_product_vpc_1.id

  tags = {
    "Name" = "investiment_product_rtb"

  }
}

resource "aws_route" "investiment_product_route" {
  route_table_id = aws_route_table.investiment_product_rtb.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id = aws_internet_gateway.investiment_product_igw.id
}

resource "aws_route_table_association" "investiment_product_ass" {
  route_table_id = aws_route_table.investiment_product_rtb.id
  subnet_id =  aws_default_subnet.investiment_product_subnet.id
}

resource "aws_instance" "investiment_product_ec2" {
  instance_type = "t2.micro"
  key_name = aws_key_pair.investiment_product_key.id
  vpc_security_group_ids = [aws_vpc.investiment_product_vpc_1.id]
  subnet_id = aws_default_subnet.investiment_product_subnet.id

  ami = data.aws_ami.investiment_product_ami.id

  root_block_device {
    volume_size = 8
  }

  tags = {
    Name = "investiment_product_ec2"
  }
}