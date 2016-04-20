Change Log
==========

Version 0.1.1 *(2016-04-20)*
----------------------------

* Bug fix of ArrayItemBinderAdapter. When calling set() it would reset scroll position, because of removing/adding all items. Instead now, it changes/updates items, and then insert/remove remaining items.

Version 0.1.0 *(2016-03-24)*
----------------------------

* Initial release.