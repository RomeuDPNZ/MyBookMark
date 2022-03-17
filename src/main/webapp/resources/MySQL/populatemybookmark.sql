/*
 *
 * Criado em: 31/07/2021
 *
 * source /home/hb/git/MyBookMark/MyBookMark/MySQL/populatemybookmark.sql
 *
 */

INSERT INTO Divs (name, position) VALUES ('Div BOTTOM', 'BOTTOM');

INSERT INTO Categories (name, description, divs) VALUES ('Category LEFT', 'Category from div left', (SELECT id FROM Divs WHERE name='Div LEFT'));
INSERT INTO Categories (name, description, divs) VALUES ('Category CENTER', 'Category from div center', (SELECT id FROM Divs WHERE name='Div CENTER'));
INSERT INTO Categories (name, description, divs) VALUES ('Category RIGHT', 'Category from div right', (SELECT id FROM Divs WHERE name='Div RIGHT'));
INSERT INTO Categories (name, description, divs) VALUES ('Category BOTTOM', 'Category from div bottom', (SELECT id FROM Divs WHERE name='Div BOTTOM'));

INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category left', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category LEFT'));
INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category center', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category CENTER'));
INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category right', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category RIGHT'));
INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category bottom', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category BOTTOM'));

INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category left', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category LEFT'));
INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category center', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category CENTER'));
INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category right', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category RIGHT'));
INSERT INTO Links (name, description, link, category) 
VALUES ('Link', 'Link from category bottom', 'http://www.google.com/', (SELECT id FROM Categories WHERE name='Category BOTTOM'));

INSERT INTO Chronometer (name, description, dateOf, category) 
VALUES ('Chronometer', 'Chronometer from category left', now(), (SELECT id FROM Categories WHERE name='Category LEFT'));
INSERT INTO Chronometer (name, description, dateOf, category) 
VALUES ('Chronometer', 'Chronometer from category center', now(), (SELECT id FROM Categories WHERE name='Category CENTER'));
INSERT INTO Chronometer (name, description, dateOf, category) 
VALUES ('Chronometer', 'Chronometer from category right', now(), (SELECT id FROM Categories WHERE name='Category RIGHT'));
INSERT INTO Chronometer (name, description, dateOf, category) 
VALUES ('Chronometer', 'Chronometer from category bottom', now(), (SELECT id FROM Categories WHERE name='Category BOTTOM'));

INSERT INTO Countdown (name, description, dateOf, category) 
VALUES ('Countdown', 'Countdown from category left', now(), (SELECT id FROM Categories WHERE name='Category LEFT'));
INSERT INTO Countdown (name, description, dateOf, category) 
VALUES ('Countdown', 'Countdown from category center', now(), (SELECT id FROM Categories WHERE name='Category CENTER'));
INSERT INTO Countdown (name, description, dateOf, category) 
VALUES ('Countdown', 'Countdown from category right', now(), (SELECT id FROM Categories WHERE name='Category RIGHT'));
INSERT INTO Countdown (name, description, dateOf, category) 
VALUES ('Countdown', 'Countdown from category bottom', now(), (SELECT id FROM Categories WHERE name='Category BOTTOM'));

INSERT INTO AnnualReminder (name, description, day, month, daysBefore, daysAfter, category) 
VALUES ('AnnualReminder', 'AnnualReminder from category left', 17, 8, 100, 100, (SELECT id FROM Categories WHERE name='Category LEFT'));
INSERT INTO AnnualReminder (name, description, day, month, daysBefore, daysAfter, category) 
VALUES ('AnnualReminder', 'AnnualReminder from category center', 17, 8, 100, 100, (SELECT id FROM Categories WHERE name='Category CENTER'));
INSERT INTO AnnualReminder (name, description, day, month, daysBefore, daysAfter, category) 
VALUES ('AnnualReminder', 'AnnualReminder from category right', 17, 8, 100, 100, (SELECT id FROM Categories WHERE name='Category RIGHT'));
INSERT INTO AnnualReminder (name, description, day, month, daysBefore, daysAfter, category) 
VALUES ('AnnualReminder', 'AnnualReminder from category bottom', 17, 8, 100, 100, (SELECT id FROM Categories WHERE name='Category BOTTOM'));

INSERT INTO MonthlyReminder (name, description, day, daysBefore, daysAfter, category) 
VALUES ('MonthlyReminder', 'MonthlyReminder from category left', 17, 100, 100, (SELECT id FROM Categories WHERE name='Category LEFT'));
INSERT INTO MonthlyReminder (name, description, day, daysBefore, daysAfter, category) 
VALUES ('MonthlyReminder', 'MonthlyReminder from category center', 17, 100, 100, (SELECT id FROM Categories WHERE name='Category CENTER'));
INSERT INTO MonthlyReminder (name, description, day, daysBefore, daysAfter, category) 
VALUES ('MonthlyReminder', 'MonthlyReminder from category right', 17, 100, 100, (SELECT id FROM Categories WHERE name='Category RIGHT'));
INSERT INTO MonthlyReminder (name, description, day, daysBefore, daysAfter, category) 
VALUES ('MonthlyReminder', 'MonthlyReminder from category bottom', 17, 100, 100, (SELECT id FROM Categories WHERE name='Category BOTTOM'));


