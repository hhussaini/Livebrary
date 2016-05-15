/**
 * Author:  Kevin Young
 * Created: May 15, 2016
 */

-- Make a book expired
-- This example sets A Game of Thrones expired for user kevinC
update CHECKED_OUT set endDate = NOW() and expired = 'y' where isbn = '9780553381689' and username = 'kevinC'


