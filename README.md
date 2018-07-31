# XmlSwingConverter
Generate Swing interfaces configured through XML

## Rationale
Lately i've been doing a lot of small Java projects that use a simple swing interface.
I've found GUI programming to be very tedious, and i'm more focused on server side work, 
however i'd like to have a simple gui for each project to easily relay what my code does 
without having to open up a command line.

There are several other Java projects with a similar goal, but they all seek to 
provide full swing functionality through xml. XmlSwingConverter emphasizes quick gui mockups
and wireframing instead. This allows the XML needed to generate the Swing
interface to be simpler and straight to the point. 

## Currently Implemented
Basic Functionality
* Layouts
    - BorderLayout
    - FlowLayout
* Containers
    - JButton
    - JLabel (Inner TextNode as jlabel title)

