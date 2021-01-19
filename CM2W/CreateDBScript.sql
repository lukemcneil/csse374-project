-- Should not be needed, but just in case...
create table Capability
(
	CapabilityName text not null
		constraint Capability_pk
			primary key,
	Description text
);

create table CoffeeMakerCapability
(
	Coffeemaker int not null,
	Capability text not null
		references Capability
			on update cascade on delete cascade,
	constraint CoffeeMakerCapability_pk
		primary key (Capability, Coffeemaker)
);

create table Controller
(
	ControllerID int not null
		constraint Controller_pk
			primary key,
	Type text,
	Street_Address text,
	ZIP_code text
);

create table CoffeeMaker
(
	MachineID int
		constraint CoffeeMaker_pk
			primary key,
	Controller int
		references Controller
			on update cascade on delete cascade,
	Description text
);

create table DrinkIngredient
(
	DrinkName text,
	IngredientName text,
	IsRequired integer,
	Quantity real,
	constraint DrinkIngredient_pk
		primary key (DrinkName, IngredientName)
);

create table DrinkType
(
	DrinkName int not null
		constraint DrinkType_pk
			primary key,
	Description text
);

create table CoffeeMakerDrink
(
	Coffeemaker int
		references CoffeeMaker
			on update cascade on delete cascade,
	Drinktype text
		references DrinkType
			on update cascade on delete cascade,
	constraint CoffeeMakerDrink_pk
		primary key (Coffeemaker, Drinktype)
);

create table Ingredient
(
	Name text
		constraint Ingredient_pk
			primary key,
	Description text
);