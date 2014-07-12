Marenwynn's ten.java submission
==============================

[![ten.java](https://cdn.mediacru.sh/hu4CJqRD7AiB.svg)](https://tenjava.com/)

This is a submission for the 2014 ten.java contest.

- __Theme:__ What random events can occur in Minecraft?
- __Time:__ Time 3 (7/12/2014 14:00 to 7/13/2014 00:00 UTC)
- __MC Version:__ 1.7.9 (latest Bukkit beta)
- __Stream URL:__ None

<!-- put chosen theme above -->

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html)
- Clone the repository: `git clone https://github.com/tenjava/Marenwynn-t3`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
-----

1. Install plugin

2. There are two craftable items that will be needed to survive 
   (they can also be used on other players while sneaking):
   
  Gauze:
    Usage: Right-click while holding
    Description: Cures one of bleeding
    Recipe:
      "SW_"
      "SW_"
      "SW_"
        'S' = String; 'W' = Wool; '_' = Nothing
  Splint:
    Usage: Right-click while holding
    Description: Cures one of broken bones
    Recipe:
      "_S_"
      "_T_"
      "_S_"
        'S' = String; 'T' = Stick; '_' = Nothing

3. Falling will have a random chance of breaking your legs;
   the greater the fall, the greater the chance. If you eat shit
   when your legs are already broken, you will bleed.

4. Being struck in combat has a random chance of making you
   bleed; the greater the damage, the greater the chance.
   Blocking will prevent this from happening. :)

5. Breaking your legs and bleeding out sucks, so stay well-stocked!
