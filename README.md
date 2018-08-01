# XmlSwingConverter
Recursively generate Swing interfaces configured through XML

## Rationale
Lately i've been doing a lot of small Java projects that use a simple swing interface.
I've found GUI programming to be very tedious, and i'm more focused on server side work, 
however i'd like to have a simple gui for each project to easily relay what my code does 
without having to open up a command line.

There are several other Java projects with a similar goal, but they all seek to 
provide full swing functionality through xml. XmlSwingConverter emphasizes quick gui mockups
and wireframing instead. This allows the XML needed to generate the Swing
interface to be simpler and straight to the point. 

## Current Limitations
* Client side code for editing a generated swing interface is ugly, with many casts for components
* Overhead from reading and parsing an xml document can lead to slower start (UI should be just as responsive once loaded however)

## Planned Features For V1
1. "Full" support for FlowLayouts and BorderLayouts (the layouts I use most often)
2. JLabels, JButtons, JTextFields, JTextAreas supported
3. XmlSwingConverters will take in a Map of <String, ActionListener> that will allow
components to link with invokable actions (think addActionListener, and the sort)

## Planned Features For V2
1. Map Component/Container References to an easily traversable object, such that you can do 
```
XmlSwingPage page = XmlSwingConverter.producePage("xmlpath");
page.get("topBorderLayout").get("titleLabel").setTitle("New Title!");
page.get("bottomBorderLayout").get("execButton").addActionListener(e -> {
    System.out.println("New Action!");
});
// And so on, using xml tages with <JLabel name="titleLabel">alkwjdlakjd</JLabel>
```
2. Write a Dtd for valid Swing xml

## Currently Implemented
Basic Functionality
* Layouts
    - BorderLayout
    - FlowLayout
    - BoxLayout (Vertical ans Horizontal)
* Containers
    - JButton
    - JLabel (Inner TextNode as JLabel title)
    - JTextField
    - JList (Only for strings)
 * Actions
    - JButton
