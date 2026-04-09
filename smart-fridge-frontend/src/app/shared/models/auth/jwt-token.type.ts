import { JwtPayload } from 'jwt-decode';

export type JwtToken = JwtPayload & {
  sub: string;
  // email: string; bruuchts maybe no?
  exp: string;
};
