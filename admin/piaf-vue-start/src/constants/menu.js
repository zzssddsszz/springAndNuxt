import { adminRoot } from "./config";
import { UserRole } from "../utils/auth.roles";

const data = [
  {
    id: "part",
    icon: "simple-icon-list",
    label: "menu.part",
    to: `${adminRoot}/part`,
    subs: [{
      icon: "simple-icon-paper-plane",
      label: "menu.pendant",
      to: `${adminRoot}/part/pendant`,
    },
      {
        icon: "simple-icon-paper-plane",
        label: "menu.product",
        to: `${adminRoot}/part/product`,
      }
    ]
  },
  {
  id: "second-menu",
  icon: "iconsminds-chemical",
  label: "menu.second-menu",
  to: `${adminRoot}/second-menu`,
  subs: [{
    icon: "simple-icon-paper-plane",
    label: "menu.second",
    to: `${adminRoot}/second-menu/second`,
  },
  ]
},
{
  id: "pages",
  icon: "iconsminds-digital-drawing",
  label: "menu.pages",
  to: "/user/login",
  subs: [{
    icon: "simple-icon-user-following",
    label: "menu.login",
    to: "/user/login",
    newWindow: true
  }, {
    icon: "simple-icon-user-follow",
    label: "menu.register",
    to: "/user/register",
    newWindow: true
  }, {
    icon: "simple-icon-user-unfollow",
    label: "menu.forgot-password",
    to: "/user/forgot-password",
    newWindow: true
  },
  {
    icon: "simple-icon-user-following",
    label: "menu.reset-password",
    to: "/user/reset-password",
    newWindow: true
  }
  ]
},
{
  id: "single",
  icon: "iconsminds-three-arrow-fork",
  label: "menu.single",
  to: `${adminRoot}/single`,
},
{
  id: "docs",
  icon: "iconsminds-library",
  label: "menu.docs",
  to: "https://piaf-vue-docs.coloredstrategies.com/",
  newWindow: true
}
];
export default data;
