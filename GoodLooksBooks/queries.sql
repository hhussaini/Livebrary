/**
 * Author:  Kevin Young
 * Created: May 15, 2016
 */

-- Make a book expired
-- This example sets A Game of Thrones expired for user kevinC
update CHECKED_OUT set endDate = NOW() and expired = 'y' where isbn = '9780553381689' and username = 'kevinC'

-- Make a book have 0 copies left
-- This example sets A Game of Thrones to have 0 copies left
update books set copiesLeft = 0 where isbn = '9780553381689'

