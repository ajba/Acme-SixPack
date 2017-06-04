start transaction;

use `Acme-SixPack`;

revoke all privileges on `Acme-SixPack`.* from 'acme-user'@'%';
revoke all privileges on `Acme-SixPack`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-SixPack`;

commit;