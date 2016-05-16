/**
 * Author:  Kevin Young
 * Created: May 15, 2016
 */

-- Make a book expired
-- This example sets A Game of Thrones expired for user kevinC
update CHECKED_OUT set endDate = NOW() and expired = 'y' where isbn = '9780553381689' and username = 'kevinC'

-- Make a book have 0 copies left. This is useful for allowing a book to be put on hold.
-- This example sets A Game of Thrones to have 0 copies left
update books set copiesLeft = 0 where isbn = '9780553381689'

-- Make A Game of Thrones have 1 copy. This is good for taking it off a hold.
update books set copiesLeft = 1 where isbn = '9780553381689'

-- Make the hold for A Game of Thrones be expired after it was available to user for 3 days
update holds set holdUntil = NOW() where isbn = '9780553381689'